package fiap.tds.services;

import fiap.tds.Dtos.ManutencaoDto;
import fiap.tds.entities.objects.Manutencao;
import fiap.tds.repositories.ManutencaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository = new ManutencaoRepository();


    public void addManutencao(ManutencaoDto dto) {
        Manutencao manutencao = new Manutencao();
        manutencao.setId(dto.id());
        manutencao.setLocal(dto.local());
        manutencao.setData_hora(dto.data_hora());
        manutencao.setDescricao(dto.descricao());
        manutencao.setNivel_Alerta(dto.nivel_Alerta());

        manutencaoRepository.add(manutencao);
    }

    private ManutencaoDto toDto(Manutencao manutencao) {
        return new ManutencaoDto(
                manutencao.getId(),
                manutencao.getLocal(),
                manutencao.getData_hora(),
                manutencao.getDescricao(),
                manutencao.getNivel_Alerta()
        );
    }

    public Optional<ManutencaoDto> getManutencaoById(int id) {
        Optional<Manutencao> manutencao = manutencaoRepository.getById(id);
        return manutencao.map(this::toDto);
    }

    public List<ManutencaoDto> getAllManutencoes() {
        return manutencaoRepository.getAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void deleteManutencao(int id) {
        manutencaoRepository.deleteById(id);
    }


}
