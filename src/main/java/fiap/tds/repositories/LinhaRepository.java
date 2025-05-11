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
    List<Linha> linhas = new ArrayList<>();


    public List<Linha> getAll() {
        var query = "SELECT * FROM \"T_TT_LINHA_METRO\"";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()){
                var linha = new Linha();
                linha.setId(result.getInt("id"));
                linha.setNome_linha(result.getString("nome_linha"));
                linha.setNumero_linha(result.getInt("numero_linha"));
//                linha.setLocalizacao_inicial(result.getString("localizacao_inicial"));   NAO VAI TER NO FRONT
//                linha.setSituacaa_atual(result.getString("situacao_atual"));


                linhas.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os alertas no banco de dados");
            e.printStackTrace();
        }
        return linhas;
    }


    public Optional<Alerta> getById(int id) {
        var query = "SELECT * from \"T_TT_LINHA_METRO\" where id = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var result = preparedStatement.executeQuery();

            // Se encontrar um resultado, cria o objeto Alerta e retorna
            if (result.next()) {
                var linha = new Linha();
                linha.setId(result.getInt("id"));
                linha.setNome_linha(result.getString("nome_linha"));
                linha.setNumero_linha(result.getInt("numero_linha"));
//                linha.setLocalizacao_inicial(result.getString("localizacao_inicial"));
//                linha.setSituacaa_atual(result.getString("situacao_atual"));

                linhas.add(linha);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }
}
