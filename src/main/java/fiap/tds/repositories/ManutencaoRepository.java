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
        var query = "Insert into \"MANUTENCAO\"(id, local, data_Hora, descricao, nivel_Alerta, deleted) values (?, ?, ?, ?, ?, ?)";
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
        var query = "UPDATE \"MANUTENCAO\" SET deleted = ? WHERE id = ?";
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
        var query = "Select * FROM \"MANUTENCAO\"";
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
                manutencao.manutencaoInformacoes(manutencao);
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
        var query = "SELECT * FROM \"MANUTENCAO\" WHERE deleted = 0";
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
                manutencao.manutencaoInformacoes(manutencao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar histórico de manutenções não deletadas");
            e.printStackTrace();
        }
        return manutencoes;
    }

    @Override
    public Optional<Manutencao> getById(int id) {
        var query = "SELECT * from \"MANUTENCAO\" where id = ?";
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
                manutencao.manutencaoInformacoes(manutencao);
                manutencoes.add(manutencao);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }

    //==================================================
    // Auxiliar para capturar a data e hora
    private static LocalDate solicitarData(Scanner scan) {
        LocalDate data = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (data == null) {
            try {
                System.out.println("Informe a Data e Hora da Manutenção (formato: dd/MM/yyyy): ");
                if (scan.hasNextLine()) {
                    scan.nextLine(); // Limpa buffer antes de ler   a entrada
                }
                String entrada = scan.nextLine().trim();
                data = LocalDate.parse(entrada, formatter); // Conversão para LocalDate
            } catch (Exception e) {
                System.out.println("Entrada inválida! Certifique-se de usar o formato correto: dd/MM/yyyy.");
            }
        }

        return data;
    }

    // Formatter para o padrão "dd/MM/yyyy"
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Gerar a data atual automaticamente
    static LocalDate dataAtual = LocalDate.now();


    public static Logger LOGGER = LogManager.getLogger(Manutencao.class);


    //Agendamento da manutenção
    public static void createManutencao(ManutencaoRepository manutencaoRepository){
        try {
            var scan = new Scanner(System.in);

            System.out.println("Id da manutenção: ");
            var id = scan.nextLine();

            System.out.println("Localização do trilho: ");
            var trilho = scan.nextLine();

            System.out.println("Descrição do problema: ");
            var descricao = scan.nextLine();

            System.out.println("NIVEL_1,   //Monitoramento Regular\n" + //Manutenção regular pode marcar o dia
                    "NIVEL_2,   //Alerta Preventivo\n" +    //Manutenção preventiva pode marcar o dia
                    "NIVEL_3,   //Alerta de Atenção\n" +    //Os restantes das manutenções por serem urgentes, a manutenção é na hora da criação
                    "NIVEL_4,   //Alerta Crítico\n" +
                    "NIVEL_5    //Alerta de Emergência\n");

            System.out.println("Nível Alerta (1-5): ");
            var nivelAlerta = scan.nextInt();

            switch (nivelAlerta){
                case 1:
                    // Captura de data e hora da manutenção com validação
                    var dataHoraManutencao1 = solicitarData(scan);
                    var manutencao1 = new Manutencao(trilho, dataHoraManutencao1, descricao, TIPOS_ALERTA.NIVEL_1);
                    manutencao1.setId(Integer.parseInt(id));
                    manutencao1.manutencaoInformacoes(manutencao1);
                    manutencaoRepository.add(manutencao1);

                    break;
                case 2:
                    // Captura de data e hora da manutenção com validação
                    var dataHoraManutencao2 = solicitarData(scan);
                    var manutencao2 = new Manutencao(trilho, dataHoraManutencao2, descricao, TIPOS_ALERTA.NIVEL_2);
                    manutencao2.setId(Integer.parseInt(id));
                    manutencao2.manutencaoInformacoes(manutencao2);
                    manutencaoRepository.add(manutencao2);
                    break;
                case 3:
                    var manutencao3 = new Manutencao(trilho, dataAtual, descricao, TIPOS_ALERTA.NIVEL_3);
                    manutencao3.setId(Integer.parseInt(id));
                    manutencao3.manutencaoInformacoes(manutencao3);
                    manutencaoRepository.add(manutencao3);
                    break;
                case 4:
                    var manutencao4 = new Manutencao(trilho, dataAtual, descricao, TIPOS_ALERTA.NIVEL_5);
                    manutencao4.setId(Integer.parseInt(id));
                    manutencao4.manutencaoInformacoes(manutencao4);
                    manutencaoRepository.add(manutencao4);
                    break;
                case 5:
                    var manutencao5 = new Manutencao(trilho, dataAtual, descricao, TIPOS_ALERTA.NIVEL_5);
                    manutencao5.setId(Integer.parseInt(id));
                    manutencao5.manutencaoInformacoes(manutencao5);
                    manutencaoRepository.add(manutencao5);
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        } catch (Exception e){
            LOGGER.error("Erro no agendamento da manutenção", e);
        }
    }

}
