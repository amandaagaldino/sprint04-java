package fiap.tds.Dtos;

import fiap.tds.entities.enums.TIPOS_ALERTA;

import java.time.LocalDate;


public record ManutencaoDto(
        int id,
        String local,
        LocalDate data_hora,
        String descricao,
        TIPOS_ALERTA nivel_Alerta
) {}