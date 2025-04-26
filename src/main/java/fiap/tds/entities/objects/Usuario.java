package fiap.tds.entities.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario extends _BaseEntity{
    private String senha;
}
