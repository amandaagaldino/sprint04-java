package fiap.tds.repositories;


import fiap.tds.entities.objects.Usuario;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    public Optional<Usuario> getById(int id) {
        var query = "SELECT * from \"T_TT_usuario\" where id_usuario = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            var result = preparedStatement.executeQuery();

            if (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id_usuario"));
                usuario.setSenha(result.getString("senha"));

                return Optional.of(usuario);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }

}
