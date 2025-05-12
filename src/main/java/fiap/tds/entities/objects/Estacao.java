package fiap.tds.entities.objects;

import java.util.Objects;

public class Estacao extends _BaseEntity {
    private String nome;
    private int id_linha;


    public Estacao() {
    }


    public Estacao(int id, String nome, int id_linha) {
        super(id);
        this.nome = nome;
        this.id_linha = id_linha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_linha() {
        return id_linha;
    }

    public void setId_linha(int id_linha) {
        this.id_linha = id_linha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Estacao estacao = (Estacao) o;
        return id_linha == estacao.id_linha && Objects.equals(nome, estacao.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome, id_linha);
    }

    @Override
    public String toString() {
        return "Estacao{" +
                "nome='" + nome + '\'' +
                ", id_linha=" + id_linha +
                '}';
    }
}
