package fiap.tds.repositories;

import fiap.tds.entities.objects.Alerta;
import fiap.tds.entities.objects.Usuario;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    public Optional<Usuario> findById(String id) {
        var query = "SELECT * from \"T_TT_USUARIO\" where id = ? and deleted = false";
        try (var connection = DatabaseConfig.getConnection()){
            var stmt = connection.prepareStatement(query);

            stmt.setString(1, id);

            var result = stmt.executeQuery();
            if (result.next()) {
                var usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setSenha(result.getString("senha"));
                usuario.setDeleted(result.getBoolean("deleted"));

                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();
    }




}
