package fiap.tds.entities.objects;

import fiap.tds.entities.enums.TIPOS_ALERTA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Alerta extends _BaseEntity {
    private TIPOS_ALERTA tipoAlerta;
    private Trilho localizacao;
    private LocalTime dataHora;
    private String descricao;

    public void alertaInformacoes(Alerta alerta){
        System.out.println("Alerta ID: " + alerta.getId());
        System.out.println("Tipo de Alerta: " + alerta.getTipoAlerta());
        System.out.println("Localização: " + alerta.getLocalizacao().toString());
        System.out.println("Hora: " + alerta.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("Descrição: " + alerta.getDescricao());
        System.out.println("Deleted: " + alerta.isDeleted());
        System.out.println(" ");
    }
}
