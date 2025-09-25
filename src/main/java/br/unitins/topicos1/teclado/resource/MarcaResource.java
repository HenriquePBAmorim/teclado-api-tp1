package br.unitins.topicos1.teclado.resource;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MarcaDTO;
import br.unitins.topicos1.teclado.dto.MarcaDTOResponse;
import br.unitins.topicos1.teclado.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {
    
    @Inject
    MarcaService service;

    @GET
    public List<MarcaDTOResponse> buscarTodas() {
        return service.findAll();
    }
    
    @GET
    @Path("/find/{nome}")
    public List<MarcaDTOResponse> buscarPorNome(@PathParam("nome") String nome ) {
        return service.findByNome(nome);
    }

    @POST
    public MarcaDTOResponse incluir(MarcaDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, MarcaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void apagar(@PathParam("id") Long id) {
        service.delete(id);
    }
}