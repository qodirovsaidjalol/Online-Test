package uz.qodirov.services;

import uz.qodirov.dto.GenericBaseDto;
import uz.qodirov.dto.GenericDto;
import uz.qodirov.entity.Auditable;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;

import java.io.Serializable;


/**
 * @param <E>
 * @param <CD>
 * @param <UD>
 * @param <K>
 * */
public interface GenericCrudService <E extends Auditable, CD extends GenericBaseDto,
        UD extends GenericDto,K extends Serializable>extends GenericService<E,K>{
ResponseEntity<Data<K>> create(CD dto);
ResponseEntity<Data<Void>> update(UD dto);
ResponseEntity<Data<Void>> delete(K id);
}
