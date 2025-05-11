package fiap.tds.services;

import fiap.tds.entities.objects.Manutencao;

public class ManutencaoService {
    public boolean validateManutencao(Manutencao manutencao){
        if(manutencao == null)
            return false;
        //ver o que precisa escrever para criar a manutencao no front (o id gera sozinho, a hora deve ser gerado sozinha?)
        if(manutencao.getLocal().isBlank() || manutencao.getDescricao().isBlank())
            return false;

        return true;
    }
}
