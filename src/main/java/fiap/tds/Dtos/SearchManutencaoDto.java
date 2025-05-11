package fiap.tds.Dtos;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import fiap.tds.entities.objects.Manutencao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//para paginação da lista de manutencoes
public record SearchManutencaoDto(
        int page,
        int totalItems,
        Optional<String> orderby,
        List<Manutencao> data
) {}