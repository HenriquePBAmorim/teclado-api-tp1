package br.unitins.topicos1.teclado.resource;

import br.unitins.topicos1.teclado.dto.SwitchDTO;
import br.unitins.topicos1.teclado.service.SwitchService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/switches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SwitchResource {
    
    @Inject
    SwitchService service;

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }

    @POST
    public Response create(@Valid SwitchDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid SwitchDTO dto) {
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