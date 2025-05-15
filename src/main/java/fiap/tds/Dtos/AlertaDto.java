package fiap.tds.Dtos;

public class AlertaDto {
    public int id;
    public String tipo;
    public String localizacao;
    public String dataHora; // ou LocalTime se preferir
    public String descricao;

    public AlertaDto(int id, String tipo, String localizacao, String dataHora, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }
}
