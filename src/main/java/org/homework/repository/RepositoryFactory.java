package org.homework.repository;

import org.homework.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {

    private final static Map<String, CrudRepository> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <T extends BaseModel<ID>, R extends CrudRepository<T, ID>, ID> CrudRepository<T, ID> of(Class<T> modelClass) {
        System.out.println("RepositoryFactory");
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new CrudRepositoryImpl(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
