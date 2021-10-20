package org.homework.service;

import org.homework.model.BaseModel;
import org.homework.repository.CrudRepository;
import org.homework.repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

public class CrudServiceImpl<T extends BaseModel<ID>, ID> implements CrudService<T, ID> {

    private final CrudRepository<T, ID> CRUD_REPOSITORY;

    public CrudServiceImpl(Class<T> modelClass) {
        CRUD_REPOSITORY = RepositoryFactory.of(modelClass);
    }

    @Override
    public Optional<T> findById(ID id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<T> findAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public void deleteById(ID id) {
        CRUD_REPOSITORY.deleteById(id);
    }

    @Override
    public T update(T t) {
        return CRUD_REPOSITORY.update(t);
    }

    @Override
    public T create(T t) {
        return CRUD_REPOSITORY.create(t);
    }
}
