package fiap.tds.resources;

import fiap.tds.Dtos.ManutencaoDto;
import fiap.tds.services.ManutencaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

@Path("/manutencoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManutencaoResource {

    public ManutencaoService manutencaoService = new ManutencaoService();

    @GET
    @Path("/all-manutencoes")
    public List<ManutencaoDto> listarManutencoes() {
        return manutencaoService.getAllManutencoes();
    }

    @GET
    @Path("/manutencoes/{id}")
    public Optional<ManutencaoDto> buscarPorId(int id) {
        return manutencaoService.getManutencaoById(id);
    }

    @POST
    @Path("/add-manutencoes")
    public void criarManutencao(ManutencaoDto dto) {
        manutencaoService.addManutencao(dto);
    }

    @DELETE
    @Path("/delete-manutencoes/{id}")
    public void deletarManutencao(int id) {
        manutencaoService.deleteManutencao(id);
    }


}
