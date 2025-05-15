package fiap.tds.repositories;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.entities.objects.Estacao;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstacaoRepository {

    public Optional<Estacao> getById(int id) {
        var query = "SELECT * from \"T_TT_ESTACAO\" where id_estacao = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var result = preparedStatement.executeQuery();

            // Se encontrar um resultado, cria o objeto Alerta e retorna
            if (result.next()) {
                Estacao estacao = new Estacao();
                estacao.setId(result.getInt("id_estacao"));
                estacao.setNome(result.getString("nm_estacao"));
                estacao.setId_linha(result.getInt("id_linha"));

                return Optional.of(estacao);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }


    public List<Estacao> getEstacoesPorLinha(int idLinha) {
        List<Estacao> estacoes = new ArrayList<>();

        String query = "SELECT * FROM \"T_TT_ESTACAO\" WHERE id_linha = ?";

        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idLinha);
            var result = preparedStatement.executeQuery();

            while (result.next()) {
                Estacao estacao = new Estacao();
                estacao.setId(result.getInt("id_estacao"));
                estacao.setNome(result.getString("nm_estacao"));
                estacao.setId_linha(result.getInt("id_linha"));
                estacoes.add(estacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estacoes;
    }
}
