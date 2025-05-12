package fiap.tds.entities.objects;


import fiap.tds.entities.enums.TIPOS_ALERTA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;


public class Manutencao extends _BaseEntity{
    private String local;
    private LocalDate data_hora;
    private String descricao;
    private TIPOS_ALERTA nivel_Alerta;


    public Manutencao() {
    }

    public Manutencao(int id, String local, LocalDate data_hora, String descricao, TIPOS_ALERTA nivel_Alerta) {
        super(id);
        this.local = local;
        this.data_hora = data_hora;
        this.descricao = descricao;
        this.nivel_Alerta = nivel_Alerta;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDate data_hora) {
        this.data_hora = data_hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TIPOS_ALERTA getNivel_Alerta() {
        return nivel_Alerta;
    }

    public void setNivel_Alerta(TIPOS_ALERTA nivel_Alerta) {
        this.nivel_Alerta = nivel_Alerta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manutencao that = (Manutencao) o;
        return Objects.equals(local, that.local) && Objects.equals(data_hora, that.data_hora) && Objects.equals(descricao, that.descricao) && nivel_Alerta == that.nivel_Alerta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), local, data_hora, descricao, nivel_Alerta);
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "local='" + local + '\'' +
                ", data_hora=" + data_hora +
                ", descricao='" + descricao + '\'' +
                ", nivel_Alerta=" + nivel_Alerta +
                '}';
    }
}
