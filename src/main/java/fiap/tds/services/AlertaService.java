package fiap.tds.services;

import fiap.tds.Dtos.AlertaDto;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.repositories.AlertaRepository;

import java.util.List;
import java.util.Optional;

public class AlertaService {

    private final AlertaRepository alertaRepository = new AlertaRepository();


    public boolean validateCard(Alerta alerta){
        if(alerta == null)
            return false;

        if(alerta.getDescricao().isBlank() || alerta.getDescricao().isBlank())
            return false;

        return true;
    }

    public Optional<AlertaDto> buscarPorId(int id) {
        return alertaRepository.getById(id).map(alerta ->
                new AlertaDto(
                        alerta.getId(),
                        alerta.getTipoAlerta().name(),
                        alerta.getLocalizacao(),
                        alerta.getDataHora().toString(),
                        alerta.getDescricao()
                )
        );
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.getAll();
    }

}
