package fiap.tds.resources;

import fiap.tds.repositories.AlertaRepository;
import fiap.tds.services.AlertaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alerta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class  AlertaResource {

    private final AlertaService alertaService = new AlertaService();


    //Buscar todos os alertas
    @GET
    @Path("/alertas")
    public Response getAlertas() {
        return Response.ok().entity(alertaService.listarTodos()).build();
    }


    //Buscar alerta por id
    @GET
    @Path("alertas-by-id/{id}")
    public Response getAlertaPorId(@PathParam("id") int id) {
        return alertaService.buscarPorId(id)
                .map(alertaDto -> Response.ok(alertaDto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Alerta não encontrado")
                        .build());
    }


    //Deletar alerta
    @DELETE
    @Path("excluir-by-id/{id}")
    public Response excluirAlerta(@PathParam("id") int id) {
        boolean excluido = alertaService.excluir(id);
        if (excluido) {
            return Response.ok().entity("Alerta excluído com sucesso").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Alerta não encontrado").build();
        }
    }



}
