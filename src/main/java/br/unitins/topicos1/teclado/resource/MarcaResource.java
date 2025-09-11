package br.unitins.topicos1.teclado.resource;

import java.util.List;

import br.unitins.topicos1.teclado.model.Marca;
import br.unitins.topicos1.teclado.repository.MarcareRepository;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {
    
    @Inject
    MarcareRepository repository;

    @GET
    public List<Marca> buscarTodas() {
        return repository.listAll();
    }
    
    @GET
    @Path("/find/{nome}")
    public List<Marca> buscarPorNome(@PathParam("nome") String nome ) {
        return repository.findByNome(nome);

    }

    @POST
    @Transactional
    public Marca incluir(Marca marca) {
        repository.persist(marca);
        return marca;
    }

        @PUT
    @Path("/{id}")
    @Transactional
    public Marca alterar(@PathParam("id") Long id, Marca marca) {
        Marca existente = repository.findById(id);
        if (existente == null) {
            throw new NotFoundException("Marca não encontrada");
        }
        existente.setNome(marca.getNome());
        existente.setDescricao(marca.getDescricao());
        return existente;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(@PathParam("id") Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Marca não encontrada");
        }
    }

}
