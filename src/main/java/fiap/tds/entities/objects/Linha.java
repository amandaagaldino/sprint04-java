package fiap.tds.entities.objects;

import java.util.Objects;

public class Linha {
    private int id;
    private String nome_linha;
    private int numero_linha;
    private String localizacao_inicial;
    private String situacaa_atual;


    public Linha() {
    }

    public Linha(int id, String nome_linha, int numero_linha, String localizacao_inicial, String situacaa_atual) {
        this.id = id;
        this.nome_linha = nome_linha;
        this.numero_linha = numero_linha;
        this.localizacao_inicial = localizacao_inicial;
        this.situacaa_atual = situacaa_atual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_linha() {
        return nome_linha;
    }

    public void setNome_linha(String nome_linha) {
        this.nome_linha = nome_linha;
    }

    public int getNumero_linha() {
        return numero_linha;
    }

    public void setNumero_linha(int numero_linha) {
        this.numero_linha = numero_linha;
    }

    public String getLocalizacao_inicial() {
        return localizacao_inicial;
    }

    public void setLocalizacao_inicial(String localizacao_inicial) {
        this.localizacao_inicial = localizacao_inicial;
    }

    public String getSituacaa_atual() {
        return situacaa_atual;
    }

    public void setSituacaa_atual(String situacaa_atual) {
        this.situacaa_atual = situacaa_atual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Linha linha = (Linha) o;
        return id == linha.id && numero_linha == linha.numero_linha && Objects.equals(nome_linha, linha.nome_linha) && Objects.equals(localizacao_inicial, linha.localizacao_inicial) && Objects.equals(situacaa_atual, linha.situacaa_atual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome_linha, numero_linha, localizacao_inicial, situacaa_atual);
    }

    @Override
    public String toString() {
        return "Linha{" +
                "id=" + id +
                ", nome_linha='" + nome_linha + '\'' +
                ", numero_linha=" + numero_linha +
                ", localizacao_inicial='" + localizacao_inicial + '\'' +
                ", situacaa_atual='" + situacaa_atual + '\'' +
                '}';
    }
}
