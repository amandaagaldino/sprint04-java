package fiap.tds.services;

import fiap.tds.Dtos.AlertaDto;
import fiap.tds.entities.objects.Alerta;
import fiap.tds.repositories.AlertaRepository;

import java.util.List;
import java.util.Optional;

public class AlertaService {

    private final AlertaRepository alertaRepository = new AlertaRepository();


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

    public boolean excluir(int id){
        if (alertaRepository.getById(id).isPresent()) {
            alertaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
