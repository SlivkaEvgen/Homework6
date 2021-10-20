package org.homework.service;

import org.homework.model.BaseModel;
import org.homework.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends BaseModel<ID>, ID> extends CrudRepository<T, ID> {

    T create(T e);

    T update(T e);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();
}
