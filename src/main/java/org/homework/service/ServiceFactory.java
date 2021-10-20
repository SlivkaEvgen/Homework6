package org.homework.service;

import org.homework.model.BaseModel;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory<T extends BaseModel<ID>,ID> implements Serializable {

    private final static Map<String, CrudService> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <T extends BaseModel<ID>, R extends CrudService<T, ID>, ID> CrudService<T, ID> of(Class<T> modelClass) {
        System.out.println("ServiceFactory");
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new CrudServiceImpl<>(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
