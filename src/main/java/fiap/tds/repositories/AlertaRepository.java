package fiap.tds.repositories;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlertaRepository implements _CrudRepository<Alerta>{

    @Override
    public void deleteById(int id) {
        var query = "DELETE FROM T_TT_ALERTA WHERE id_alerta = ?";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
   }

    @Override
    public List<Alerta> getAll() {
        var alertas = new ArrayList<Alerta>();
        var query = "SELECT * FROM \"T_TT_ALERTA\"";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()){
                var alerta = new Alerta();
                alerta.setId(result.getInt("id_alerta"));
                alerta.setTipoAlerta(TIPOS_ALERTA.valueOf(result.getString("tipo_alerta")));
                alerta.setLocalizacao(result.getString("localizacao"));
                alerta.setDataHora(result.getTimestamp("dt_hr_alerta").toLocalDateTime().toLocalTime());
                alerta.setDescricao(result.getString("des_alerta"));

                alertas.add(alerta);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return alertas;
    }


    @Override
    public Optional<Alerta> getById(int id) {
        var query = "SELECT * from \"T_TT_ALERTA\" where id_alerta = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var result = preparedStatement.executeQuery();

            // Se encontrar um resultado, cria o objeto Alerta e retorna
            if (result.next()) {
                Alerta alerta = new Alerta();
                alerta.setId(result.getInt("id_alerta"));
                alerta.setTipoAlerta(TIPOS_ALERTA.valueOf(result.getString("tipo_alerta")));
                alerta.setLocalizacao(result.getString("localizacao"));
                alerta.setDataHora(result.getTimestamp("dt_hr_alerta").toLocalDateTime().toLocalTime());
                alerta.setDescricao(result.getString("des_alerta"));

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
