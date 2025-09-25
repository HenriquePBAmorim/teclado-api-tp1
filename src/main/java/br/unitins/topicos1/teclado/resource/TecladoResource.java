// TecladoResource.java
package br.unitins.topicos1.teclado.resource;

import java.util.List;
import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.dto.TecladoDTOResponse;
import br.unitins.topicos1.teclado.service.TecladoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/teclados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TecladoResource {

    @Inject
    TecladoService service;

    @GET
    public List<TecladoDTOResponse> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/find/{nome}")
    public List<TecladoDTOResponse> buscarPorNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    public TecladoDTOResponse incluir(TecladoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, TecladoDTO dto) {
        service.update(id, dto);
    }   
    
    @DELETE
    @Path("/{id}")
    public void apagar(@PathParam("id") Long id) {
        service.delete(id);
    }
}