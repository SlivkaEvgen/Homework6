package org.homework.repository;

import org.homework.model.BaseModel;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends BaseModel<ID>, ID> {

    T create(T e);

    T update(T e);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();
}