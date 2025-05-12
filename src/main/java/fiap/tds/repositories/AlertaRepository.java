package fiap.tds.repositories;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlertaRepository implements _CrudRepository<Alerta>{
    List<Alerta> alertas = new ArrayList<Alerta>();

    @Override
    public void add(Alerta object) {
        var query = "Insert into \"T_TT_ALERTA\"(id_alerta , tipo_alerta, localizacao, dt_hr_alerta, des_alerta) values (?, ?, ?, ?, ?)";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, object.getId());
            stmt.setString(2, object.getTipoAlerta().toString());
            stmt.setString(3, object.getLocalizacao());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(object.getDataHora().withSecond(0).withNano(0).atDate(java.time.LocalDate.now())));
            stmt.setString(5, object.getDescricao());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Erro ao inserir no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
//        var query = "UPDATE \"T_TT_ALERTA\" SET deleted = ? WHERE id = ?";
//        try (var connection = DatabaseConfig.getConnection()) {
//            var stmt = connection.prepareStatement(query);
//            stmt.setInt(1, 1); // Define 1 como verdadeiro para a coluna "deleted"
//            stmt.setInt(2, id); // Define o ID do alerta
//            stmt.executeUpdate();
//            System.out.println("Alerta marcado como deletado.");
//        } catch (SQLException e) {
//            System.out.println("Erro ao marcar o alerta como deletado no banco de dados");
//            e.printStackTrace();
//        }
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
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os alertas no banco de dados");
            e.printStackTrace();
        }
        return alertas;
    }


    @Override
    public Optional<Alerta> getById(int id) {
        var query = "SELECT * from \"T_TT_ALERTA\" where id = ?";
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
