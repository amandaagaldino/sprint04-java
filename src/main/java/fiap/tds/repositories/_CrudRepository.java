package fiap.tds.repositories;

import java.util.List;
import java.util.Optional;

public interface _CrudRepository<T> {

    //remoção fisica
    void deleteById(int id);

    List<T> getAll();

    Optional<T> getById(int id);
}
