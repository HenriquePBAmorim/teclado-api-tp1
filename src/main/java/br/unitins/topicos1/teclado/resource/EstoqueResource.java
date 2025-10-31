package br.unitins.topicos1.teclado.resource;

import br.unitins.topicos1.teclado.dto.EstoqueDTO;
import br.unitins.topicos1.teclado.service.EstoqueService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueResource {
    
    @Inject
    EstoqueService service;

    // Estoque é 1:1, então não faz sentido ter findAll() ou findByNome()
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @POST
    public Response create(@Valid EstoqueDTO dto) {
        return Response.status(Status.CREATED).entity(service.createForTeclado(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid EstoqueDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}