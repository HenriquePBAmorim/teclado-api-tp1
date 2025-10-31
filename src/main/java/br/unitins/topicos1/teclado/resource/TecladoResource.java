package br.unitins.topicos1.teclado.resource;

import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.service.TecladoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/teclados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TecladoResource {

    @Inject
    TecladoService service;

    @GET
    public Response buscarTodos() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/find/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }

    @POST
    public Response incluir(@Valid TecladoDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid TecladoDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }    
    
    @DELETE
    @Path("/{id}")
    public Response apagar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}