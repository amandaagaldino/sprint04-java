package fiap.tds.resources;

import fiap.tds.repositories.AlertaRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alerta")
public class AlertaResource {

    public AlertaRepository alertaRepository = new AlertaRepository();

    @GET
    @Path("/alertas")
    @Produces(MediaType.APPLICATION_JSON)
    //@Fallback(fallbackMethod = "rateLimitFallback")
    //@Timeout
    public Response getAlertas() {
        return Response.ok().entity(alertaRepository.getAll()).build();
    }

//    public Response rateLimitFallback(){
//        return Response.status(Response.Status.TOO_MANY_REQUESTS)
//                .entity("Voce excedeu o limite de requisições")
//                .build();
//    }





}
