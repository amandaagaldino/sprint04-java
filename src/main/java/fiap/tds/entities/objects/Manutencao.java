package fiap.tds.entities.objects;


import fiap.tds.entities.enums.TIPOS_ALERTA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Manutencao extends _BaseEntity{
    private String local;
    private LocalDate data_Hora;
    private String descricao;
    private TIPOS_ALERTA nivel_Alerta;


    public void manutencaoInformacoes(Manutencao manutencao){
        System.out.println("Manutenção ID: " + manutencao.getId());
        System.out.println("Local: " + manutencao.getLocal());
        System.out.println("Data: " + manutencao.getData_Hora());
        System.out.println("Descrição: " + manutencao.getDescricao());
        System.out.println("Tipo de Alerta: " + manutencao.getNivel_Alerta());
        System.out.println("Deleted: " + manutencao.isDeleted());
        System.out.println(" ");
    }
}
