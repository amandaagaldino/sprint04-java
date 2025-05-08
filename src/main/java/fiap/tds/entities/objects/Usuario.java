package fiap.tds.entities.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


public class Usuario extends _BaseEntity{
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, boolean deleted, String senha) {
        super(id, deleted);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "senha='" + senha + '\'' +
                '}';
    }
}
