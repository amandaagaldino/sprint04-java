package fiap.tds.resources;

import fiap.tds.entities.objects.Alerta;
import fiap.tds.repositories.AlertaRepository;
import fiap.tds.services.AlertaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alerta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class  AlertaResource {

    public AlertaRepository alertaRepository = new AlertaRepository();

    private final AlertaService alertaService = new AlertaService();

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

    @GET
    @Path("/{id}")
    public Response getAlertaPorId(@PathParam("id") int id) {
        return alertaService.buscarPorId(id)
                .map(alertaDto -> Response.ok(alertaDto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Alerta não encontrado")
                        .build());
    }





}
