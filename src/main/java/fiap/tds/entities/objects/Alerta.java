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
    private String localizacao;
    private LocalTime dataHora;
    private String descricao;

}
