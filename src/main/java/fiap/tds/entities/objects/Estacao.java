package fiap.tds.entities.objects;

import java.util.Objects;

public class Estacao {
    private int id;
    private String nome;
    private int id_linha;


    public Estacao() {
    }

    public Estacao(int id, String nome, int id_linha) {
        this.id = id;
        this.nome = nome;
        this.id_linha = id_linha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Estacao estacao = (Estacao) o;
        return id == estacao.id && id_linha == estacao.id_linha && Objects.equals(nome, estacao.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, id_linha);
    }

    @Override
    public String toString() {
        return "Estacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", id_linha=" + id_linha +
                '}';
    }
}
