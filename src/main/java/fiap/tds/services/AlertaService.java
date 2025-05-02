package fiap.tds.services;

import fiap.tds.entities.objects.Alerta;

public class AlertaService {

    public boolean validateCard(Alerta alerta){
        if(alerta == null)
            return false;

        if(alerta.getDescricao().isBlank() || alerta.getDescricao().isBlank())
            return false;

        return true;
    }
}
