package br.unitins.topicos1.teclado.resource;

import java.util.List;

import br.unitins.topicos1.teclado.model.Teclado;
import br.unitins.topicos1.teclado.repository.TecladoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Teclados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TecladoResource {

    @Inject
    TecladoRepository repository;

    @GET
    public List<Teclado> buscarTodos() {
        return repository.listAll();
    }

    @GET
    @Path("/find{nome}")
    public List<Teclado> buscarPorNome(@PathParam("nome") String nome) {
        return repository.findBynNome(nome);
    }

    @POST
    @Transactional
    public Teclado incluir(Teclado teclado) {
        repository.persist(teclado);
        return teclado;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Teclado alerar(@PathParam("id") Long id, Teclado teclado) {
        Teclado existente = (Teclado) repository.findById(id);
        if (existente == null) {
            throw new NotFoundException("Teclado não encontrado");
        }
        existente.setNome(teclado.getNome());
        existente.setMarca(teclado.getMarca());
        existente.setModelo(teclado.getModelo());
        existente.setTipo(teclado.getTipo());
        existente.setIdoma(teclado.getIdoma());
        existente.setComFio(teclado.getComFio());
        existente.setIluminacaoRgb(teclado.getIluminacaoRgb());
        existente.setPreco(teclado.getPreco());
        return existente;
    }    

   @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(@PathParam("id") Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Teclado não encontrado");
        }
    }
}
