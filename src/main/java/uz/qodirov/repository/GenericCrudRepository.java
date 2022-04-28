package uz.qodirov.repository;

import uz.qodirov.entity.Auditable;

import java.io.Serializable;

public interface GenericCrudRepository<E extends Auditable, K extends Serializable>extends GenericRepository<E,K> {

K creat(E model);
void update(E model);
void delete(K id);


}
