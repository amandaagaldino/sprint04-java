package fiap.tds.resources;

import fiap.tds.repositories.LinhaRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/linha")
public class LinhaResource {

    public LinhaRepository linhaRepository = new LinhaRepository();


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLinhaById(@PathParam("id") int id) {
        var linha = linhaRepository.getById(id).orElse(null);

        if (linha == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Linha com ID " + id + " não foi encontrada")
                    .build();
        }
        return Response.ok().entity(linha).build();
    }

    @GET
    @Path("/linhas")
    @Produces(MediaType.APPLICATION_JSON)
    //@Fallback(fallbackMethod = "rateLimitFallback")
    //@Timeout
    public Response getAllLinhas() {
        return Response.ok().entity(linhaRepository.getAll()).build();
    }

}
