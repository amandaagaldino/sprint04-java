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
    List<Estacao> estacaos = new ArrayList<Estacao>();

    public List<Estacao> getAll() {
        var query = "SELECT * FROM \"T_TT_ESTACAO\"";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()){
                var estacao = new Estacao();
                estacao.setId(result.getInt("id_estacao"));
                estacao.setNome(result.getString("nm_estacao"));
                estacao.setId_linha(result.getInt("id_linha"));


                estacaos.add(estacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os alertas no banco de dados");
            e.printStackTrace();
        }
        return estacaos;
    }


    public Optional<Estacao> getById(int id) {
        var query = "SELECT * from \"T_TT_ESTACAO\" where id = ?";
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

               estacaos.add(estacao);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }
}
