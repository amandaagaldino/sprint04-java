package fiap.tds.repositories;


import fiap.tds.entities.objects.Usuario;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    List<Usuario> usuarios = new ArrayList<>();

    public Optional<Usuario> getById(int id) {
        var query = "SELECT * from \"T_TT_usuario\" where id = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var result = preparedStatement.executeQuery();

            if (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id_usuario"));
                usuario.setSenha(result.getString("senha"));

                //usuarios.add(usuario);
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
