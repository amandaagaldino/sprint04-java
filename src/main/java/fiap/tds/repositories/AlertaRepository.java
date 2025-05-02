package fiap.tds.repositories;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlertaRepository implements _CrudRepository<Alerta>{
    List<Alerta> alertas = new ArrayList<>();

    @Override
    public void add(Alerta object) {
        var query = "Insert into \"Alerta\"(id, tipoAlerta, localizacao, dataHora, descricao, deleted) values (?, ?, ?, ?, ?, ?)";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, object.getId());
            stmt.setString(2, object.getTipoAlerta().toString());
            stmt.setString(3, object.getLocalizacao());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(object.getDataHora().withSecond(0).withNano(0).atDate(java.time.LocalDate.now())));
            stmt.setString(5, object.getDescricao());
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
        var query = "UPDATE \"Alerta\" SET deleted = ? WHERE id = ?";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, 1); // Define 1 como verdadeiro para a coluna "deleted"
            stmt.setInt(2, id); // Define o ID do alerta
            stmt.executeUpdate();
            System.out.println("Alerta marcado como deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao marcar o alerta como deletado no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public List<Alerta> getAll() {
        var alertas = new ArrayList<Alerta>();
        var query = "SELECT * FROM \"Alerta\"";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()){
                var alerta = new Alerta();
                alerta.setId(result.getInt("id"));
                alerta.setTipoAlerta(TIPOS_ALERTA.valueOf(result.getString("tipoAlerta")));
                alerta.setLocalizacao(result.getString("localizacao"));
                alerta.setDataHora(result.getTimestamp("dataHora").toLocalDateTime().toLocalTime());
                alerta.setDescricao(result.getString("descricao"));
                alerta.setDeleted(result.getBoolean("deleted"));

                alertas.add(alerta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os alertas no banco de dados");
            e.printStackTrace();
        }
        return alertas;
    }

    @Override
    public List<Alerta> get() {
        var alertas = new ArrayList<Alerta>();
        var query = "SELECT * FROM \"Alerta\" WHERE deleted = 0";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()) {
                var alerta = new Alerta();
                alerta.setId(result.getInt("id"));
                alerta.setTipoAlerta(TIPOS_ALERTA.valueOf(result.getString("tipoAlerta")));
                alerta.setLocalizacao(result.getString("localizacao"));
                alerta.setDataHora(result.getTimestamp("dataHora").toLocalDateTime().toLocalTime());
                alerta.setDescricao(result.getString("descricao"));
                alerta.setDeleted(result.getBoolean("deleted"));

                alertas.add(alerta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os alertas não deletados no banco de dados");
            e.printStackTrace();
        }
        return alertas;
    }

    @Override
    public Optional<Alerta> getById(int id) {
        var query = "SELECT * from \"Alerta\" where id = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var resultSet = preparedStatement.executeQuery();

            // Se encontrar um resultado, cria o objeto Alerta e retorna
            if (resultSet.next()) {
                Alerta alerta = new Alerta();
                alerta.setId(resultSet.getInt("id"));
                alerta.setTipoAlerta(TIPOS_ALERTA.valueOf(resultSet.getString("tipoAlerta")));
                alerta.setLocalizacao(resultSet.getString("localizacao"));
                alerta.setDescricao(resultSet.getString("descricao"));
                alerta.setDataHora(resultSet.getTimestamp("dataHora").toLocalDateTime().toLocalTime());
                alerta.setDeleted(resultSet.getBoolean("deleted"));

                alertas.add(alerta);

                return Optional.of(alerta);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }
}
