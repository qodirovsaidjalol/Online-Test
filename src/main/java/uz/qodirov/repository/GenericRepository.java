package uz.qodirov.repository;

import uz.qodirov.entity.Auditable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<E extends Auditable, K extends Serializable> {
    List<E> list();

    Optional<E> get(K id);

}
