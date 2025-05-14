package fiap.tds.services;

import fiap.tds.Dtos.EstacaoDto;
import fiap.tds.repositories.EstacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EstacaoService {
    private final EstacaoRepository estacaoRepository = new EstacaoRepository();

    public List<EstacaoDto> buscarEstacoesPorLinha(int idLinha) {
        return estacaoRepository.getEstacoesPorLinha(idLinha)
                .stream()
                .map(estacao -> new EstacaoDto(estacao.getId(), estacao.getNome()))
                .collect(Collectors.toList());
    }

    public Optional<EstacaoDto> buscarEstacaoPorId(int id) {
        return estacaoRepository.getById(id)
                .map(estacao -> new EstacaoDto(estacao.getId(), estacao.getNome()));
    }
}
