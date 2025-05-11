package fiap.tds.repositories;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.entities.objects.Manutencao;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManutencaoRepository implements _CrudRepository<Manutencao>{
    List<Manutencao> manutencoes = new ArrayList<>();

    @Override
    public void add(Manutencao object) {
        var query = "Insert into \"T_TT_MANUTENCAO\"(id, local, data_Hora, descricao, nivel_Alerta, deleted) values (?, ?, ?, ?, ?, ?)";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, object.getId());
            stmt.setString(2, object.getLocal());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(object.getData_Hora().atStartOfDay()));
            stmt.setString(4, object.getDescricao());
            stmt.setString(5, object.getNivel_Alerta().toString());
            stmt.setBoolean(6, false);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Erro ao inserir no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        var query = "UPDATE \"T_TT_MANUTENCAO\" SET deleted = ? WHERE id = ?";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, 1); // Define 1 como verdadeiro para a coluna "deleted"
            stmt.setInt(2, id); // Define o ID do alerta
            stmt.executeUpdate();
            System.out.println("Manutenção marcada como deletada.");
        } catch (SQLException e) {
            System.out.println("Erro ao marcar o manutenção como deletada no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public List<Manutencao> getAll() {
        var manutencoes = new ArrayList<Manutencao>();
        var query = "Select * FROM \"T_TT_MANUTENCAO\"";
        try (var connection = DatabaseConfig.getConnection()){
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while(result.next()){
                var manutencao = new Manutencao();
                manutencao.setId(result.getInt("id"));
                manutencao.setNivel_Alerta(TIPOS_ALERTA.valueOf(result.getString("Nivel_Alerta")));
                manutencao.setLocal(result.getString("local"));
                manutencao.setData_Hora(result.getDate("data_Hora").toLocalDate());
                manutencao.setDescricao(result.getString("descricao"));
                manutencao.setDeleted(result.getBoolean("deleted"));
                manutencoes.add(manutencao);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar histórico de manutenções");
            e.printStackTrace();
        }
        return manutencoes;
    }

    @Override
    public List<Manutencao> get() {
        var manutencoes = new ArrayList<Manutencao>();
        var query = "SELECT * FROM \"T_TT_MANUTENCAO\" WHERE deleted = 0";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()) {
                var manutencao = new Manutencao();
                manutencao.setId(result.getInt("id"));
                manutencao.setNivel_Alerta(TIPOS_ALERTA.valueOf(result.getString("Nivel_Alerta")));
                manutencao.setLocal(result.getString("local"));
                manutencao.setData_Hora(result.getDate("data_Hora").toLocalDate());
                manutencao.setDescricao(result.getString("descricao"));
                manutencao.setDeleted(result.getBoolean("deleted"));
                manutencoes.add(manutencao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar histórico de manutenções não deletadas");
            e.printStackTrace();
        }
        return manutencoes;
    }

    @Override
    public Optional<Manutencao> getById(int id) {
        var query = "SELECT * from \"T_TT_MANUTENCAO\" where id = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var resultSet = preparedStatement.executeQuery();

            // Se encontrar um resultado, cria o objeto Alerta e retorna
            if (resultSet.next()) {
                Manutencao manutencao = new Manutencao();
                manutencao.setId(resultSet.getInt("id"));
                manutencao.setLocal(resultSet.getString("local"));
                manutencao.setNivel_Alerta(TIPOS_ALERTA.valueOf(resultSet.getString("Nivel_Alerta")));
                manutencao.setData_Hora(resultSet.getDate("data_Hora").toLocalDate());
                manutencao.setDescricao(resultSet.getString("descricao"));
                manutencao.setDeleted(resultSet.getBoolean("deleted"));
                manutencoes.add(manutencao);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }


    public List<Manutencao> search(Optional<String> local, Optional<String> descricao, Optional<String> orderby) {
        StringBuilder query = new StringBuilder("SELECT * FROM \"T_TT_MANUTENCAO\" WHERE deleted = 0");

        if (local.isPresent()) query.append(" AND LOWER(local) LIKE ?");
        if (descricao.isPresent()) query.append(" AND LOWER(descricao) LIKE ?");

        if (orderby.isPresent()) {
            switch (orderby.get()) {
                case "local" -> query.append(" ORDER BY local");
                case "descricao" -> query.append(" ORDER BY descricao");
                case "data" -> query.append(" ORDER BY data_Hora");
                default -> query.append(" ORDER BY id");
            }
        } else {
            query.append(" ORDER BY id");
        }

        var results = new ArrayList<Manutencao>();
        try (var conn = DatabaseConfig.getConnection();
             var stmt = conn.prepareStatement(query.toString())) {

            int paramIndex = 1;
            if (local.isPresent()) stmt.setString(paramIndex++, "%" + local.get().toLowerCase() + "%");
            if (descricao.isPresent()) stmt.setString(paramIndex++, "%" + descricao.get().toLowerCase() + "%");

            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Manutencao m = new Manutencao();
                m.setId(resultSet.getInt("id"));
                m.setLocal(resultSet.getString("local"));
                m.setDescricao(resultSet.getString("descricao"));
                m.setData_Hora(resultSet.getDate("data_Hora").toLocalDate());
                m.setNivel_Alerta(TIPOS_ALERTA.valueOf(resultSet.getString("Nivel_Alerta")));
                m.setDeleted(resultSet.getBoolean("deleted"));
                results.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


}
