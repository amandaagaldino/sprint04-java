package fiap.tds.entities.objects;


import fiap.tds.entities.enums.STATUS_DESGASTE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Trilho extends _BaseEntity{
    private String localizacao;
    private STATUS_DESGASTE status;

    //Tranforma um dado Trilho em uma string
    @Override
    public String toString() {
        return status != null ? (localizacao + " - " + status) : localizacao;
    }

    // tranforma string que vendo do bando ce dados em um dado Trilho
    public static Trilho fromString(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("String de localização nula ou vazia");
        }
        // Exemplo: Fazendo parsing de "localizacao - status", ajustando ao formato esperado
        String[] parts = str.split(" - ");
        String localizacao = parts[0];
        STATUS_DESGASTE status = parts.length > 1 ? STATUS_DESGASTE.valueOf(parts[1]) : null;
        return new Trilho(localizacao, status);
    }
}
