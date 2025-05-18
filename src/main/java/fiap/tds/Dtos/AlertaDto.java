package fiap.tds.Dtos;

public record AlertaDto (
        int id,
        String tipo,
        String localizacao,
        String dataHora,
        String descricao
) {}

