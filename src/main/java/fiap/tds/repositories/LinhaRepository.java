package fiap.tds.repositories;

import fiap.tds.entities.objects.Alerta;
import fiap.tds.entities.objects.Estacao;
import fiap.tds.entities.objects.Linha;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LinhaRepository {

    public Optional<Linha> getById(int id) {
        var query = "SELECT * FROM \"T_TT_LINHA_METRO\" WHERE id_linha = ?";

        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            var result = preparedStatement.executeQuery();

            if (result.next()) {
                Linha linha = new Linha();
                linha.setId(result.getInt("id_linha"));
                linha.setNome_linha(result.getString("nm_linha"));

                return Optional.of(linha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
