package br.unitins.topicos1.teclado.resource;

import java.util.List;
import br.unitins.topicos1.teclado.dto.EstadoDTO;
import br.unitins.topicos1.teclado.dto.EstadoDTOResponse;
import br.unitins.topicos1.teclado.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {
    @Inject
    EstadoService service;

    @GET
    public List<EstadoDTOResponse> buscarTodos() {
        return service.findAll();
    }
    @GET
    @Path("/find/{nome}")
    public List<EstadoDTOResponse> buscarPorNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }
    @POST
    public EstadoDTOResponse incluir(EstadoDTO dto) {
        return service.create(dto);
    }
    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, EstadoDTO dto) {
        service.update(id, dto);
    }
    @DELETE
    @Path("/{id}")
    public void apagar(@PathParam("id") Long id) {
        service.delete(id);
    }
}