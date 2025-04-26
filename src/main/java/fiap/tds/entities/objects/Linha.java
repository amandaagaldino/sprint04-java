package fiap.tds.entities.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Linha {
    private String nome;
    private String status;


    public void iniciarOperacao(){
        System.out.println("Operação " + nome + " iniciada!");
    }
}
