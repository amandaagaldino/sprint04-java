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

}
