package fiap.tds.entities.objects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class _BaseEntity {
    private int id;
    private boolean deleted;
}
