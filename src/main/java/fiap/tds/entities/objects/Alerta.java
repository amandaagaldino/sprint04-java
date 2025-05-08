package fiap.tds.entities.objects;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Objects;


public class Alerta extends _BaseEntity {
    private TIPOS_ALERTA tipoAlerta;
    private String localizacao;
    private LocalTime dataHora;
    private String descricao;


    public Alerta() {
    }


    public Alerta(int id, boolean deleted, TIPOS_ALERTA tipoAlerta, String localizacao, LocalTime dataHora, String descricao) {
        super(id, deleted);
        this.tipoAlerta = tipoAlerta;
        this.localizacao = localizacao;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    public TIPOS_ALERTA getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(TIPOS_ALERTA tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Alerta alerta = (Alerta) o;
        return tipoAlerta == alerta.tipoAlerta && Objects.equals(localizacao, alerta.localizacao) && Objects.equals(dataHora, alerta.dataHora) && Objects.equals(descricao, alerta.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipoAlerta, localizacao, dataHora, descricao);
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "tipoAlerta=" + tipoAlerta +
                ", localizacao='" + localizacao + '\'' +
                ", dataHora=" + dataHora +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
