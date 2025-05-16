package fiap.tds.resources;

import fiap.tds.repositories.LinhaRepository;
import fiap.tds.services.LinhaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/linha")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LinhaResource {

    public LinhaService linhaService = new LinhaService();

    //Busca a linha com o id
    @GET
    @Path("{id}")
    public Response getLinhaById(@PathParam("id") int id) {
        return linhaService.buscarLinhaPorId(id)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Linha " + id + " não encontrada.")
                        .build());
    }

}
