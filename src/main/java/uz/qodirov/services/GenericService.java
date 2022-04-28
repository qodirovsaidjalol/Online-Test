package uz.qodirov.services;

import uz.qodirov.entity.Auditable;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericService<E extends Auditable,K extends Serializable> {
    ResponseEntity<Data<E>> get(K id);

    ResponseEntity<Data<List<E>>> getList();
}
