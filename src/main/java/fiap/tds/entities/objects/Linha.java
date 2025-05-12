package fiap.tds.entities.objects;

import java.util.Objects;

public class Linha extends _BaseEntity{
    private String nome_linha;


    public Linha() {
    }

    public Linha(int id, String nome_linha) {
        super(id);
        this.nome_linha = nome_linha;
    }

    public String getNome_linha() {
        return nome_linha;
    }

    public void setNome_linha(String nome_linha) {
        this.nome_linha = nome_linha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Linha linha = (Linha) o;
        return Objects.equals(nome_linha, linha.nome_linha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome_linha);
    }

    @Override
    public String toString() {
        return "Linha{" +
                "nome_linha='" + nome_linha + '\'' +
                '}';
    }
}
