package fiap.tds.repositories;

import java.util.List;
import java.util.Optional;

public interface _CrudRepository<T> {
    void add(T t);

    //remoção logica
    void deleteById(int id);

    //traz tudo idependente do status
    List<T> getAll();

    List<T> get();

    Optional<T> getById(int id);
}
