package fiap.tds.resources;

import fiap.tds.Dtos.SearchManutencaoDto;
import fiap.tds.entities.objects.Manutencao;
import fiap.tds.repositories.ManutencaoRepository;
import fiap.tds.services.ManutencaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Path("/manutencoes")
public class ManutencaoResource {
    private static final List<Manutencao> manutencoes = new ArrayList<>(); // simulação de banco
    private static final int PAGE_SIZE = 10;

    public ManutencaoRepository manutencaoRepository = new ManutencaoRepository();

    @GET
    @Path("/search") //
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(
            @QueryParam("local") Optional<String> local,
            @QueryParam("descricao") Optional<String> descricao,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("order") Optional<String> orderby
    ) {
        var resultados = manutencaoRepository.search(local, descricao, orderby);
        int total = resultados.size();

        int start = (page - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, total);

        if (start >= total && total > 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        List<Manutencao> pagina = resultados.subList(start, end)
                .stream()
                .map(m -> new Manutencao(
                        m.getId(),
                        m.isDeleted(),
                        m.getLocal(),
                        m.getData_Hora(),
                        m.getDescricao(),
                        m.getNivel_Alerta()))
                .toList();

        var responseDto = new SearchManutencaoDto(page, total, orderby, pagina);
        return Response.ok(responseDto).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManutencao(Manutencao manutencao){
        if(manutencao == null || !new ManutencaoService().validateManutencao(manutencao))
            return Response.status(Response.Status.BAD_REQUEST).build();

        manutencaoRepository.add(manutencao);

        return Response.status(Response.Status.CREATED).build();
    }
}
