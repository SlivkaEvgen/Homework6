package org.homework.service;

import org.homework.model.Developer;
import org.homework.repository.DeveloperCrudRepository;
import org.homework.repository.DeveloperCrudRepositoryImpl;

import java.util.List;

public class DeveloperServiceImpl extends CrudServiceImpl<Developer,Long> implements DeveloperService {

    private final DeveloperCrudRepository CRUD_REPOSITORY;
    private static DeveloperServiceImpl developerService;

    private DeveloperServiceImpl(Class<Developer> modelClass) {
        super(modelClass);
        this.CRUD_REPOSITORY=DeveloperCrudRepositoryImpl.getDeveloperService();
    }

    public static DeveloperServiceImpl getDeveloperService() {
        if (developerService == null) {
            synchronized (DeveloperServiceImpl.class) {
                if (developerService == null) {
                    developerService = new DeveloperServiceImpl(Developer.class);
                }
            }
        }
        return developerService;
    }

    @Override
    public Object getSumSalariesDevelopersOfOneProject(Long projectId) {
        return CRUD_REPOSITORY.getSumSalariesDevelopersOfOneProject(projectId);
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        return CRUD_REPOSITORY.getDevelopersFromOneProject(projectId);
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        return CRUD_REPOSITORY.getDevelopersByActivity(nameActivity);
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        return CRUD_REPOSITORY.getDevelopersByLevel(nameLevel);
    }
}
