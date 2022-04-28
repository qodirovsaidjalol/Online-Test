package uz.qodirov.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.criteria.GenericCriteria;
import uz.qodirov.entity.Auditable;
import uz.qodirov.repository.AbstractRepository;

/**
 * THIS IS A BASE ABSTRACT CLASS THAT PROVIDES CONNECTION WITH MONGO DB
 *
 * @param <C> -> Criteria
 * @param <E> -> Collection
 */

public class GenericDao <C extends GenericCriteria, E extends Auditable>extends AbstractRepository {
    protected MongoDatabase db = ApplicationContextHolder.getBean(MongoDatabase.class);
    protected MongoCollection<E> collection;

    public GenericDao(Class<E> clazz) {
        this.collection = db.getCollection(clazz.getSimpleName(), clazz);
    }}
