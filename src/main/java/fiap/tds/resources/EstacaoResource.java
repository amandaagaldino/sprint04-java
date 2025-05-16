package fiap.tds.resources;

import fiap.tds.Dtos.EstacaoDto;
import fiap.tds.services.EstacaoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/estacao-linha")
@Produces(MediaType.APPLICATION_JSON)
public class EstacaoResource {

    private final EstacaoService estacaoService = new EstacaoService();

    //Busca todas as estacoes de acordo com o id da linha
    @GET
    @Path("/{idLinha}/estacoes")
    public Response getEstacoesPorLinha(@PathParam("idLinha") int idLinha) {
        List<EstacaoDto> estacoes = estacaoService.buscarEstacoesPorLinha(idLinha);
        return Response.ok(estacoes).build();
    }

    //Busca a estacao com o id
    @GET
    @Path("/estacoes/{id}")
    public Response getEstacaoPorId(@PathParam("id") int id) {
        return estacaoService.buscarEstacaoPorId(id)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Estação não encontrada.")
                        .build());
    }
}
