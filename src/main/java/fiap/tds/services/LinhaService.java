package fiap.tds.services;

import fiap.tds.Dtos.LinhaDto;
import fiap.tds.repositories.LinhaRepository;

import java.util.Optional;

public class LinhaService {
    private final LinhaRepository linhaRepository = new LinhaRepository();

    public Optional<LinhaDto> buscarLinhaPorId(int id) {
        return linhaRepository.getById(id)
                .map(linha -> new LinhaDto(linha.getId(), linha.getNome_linha()));
    }
}
