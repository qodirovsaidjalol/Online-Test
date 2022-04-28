package uz.qodirov.services;

import uz.qodirov.mappers.GenericBaseMapper;
import uz.qodirov.repository.AbstractRepository;

public abstract  class AbstractService<R extends AbstractRepository, M extends GenericBaseMapper> {
protected final R repository;
protected final M mapper;


    public AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
