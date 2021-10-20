package org.homework.service;

import org.homework.model.Developer;
import org.homework.repository.DeveloperCrudRepository;
import org.homework.repository.DeveloperCrudRepositoryImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class DeveloperServiceImpl implements DeveloperService, Serializable {

    private final DeveloperCrudRepository CRUD_REPOSITORY = DeveloperCrudRepositoryImpl.getDeveloperCrudRepository();
    private static DeveloperServiceImpl developerService;

    public static DeveloperServiceImpl getDeveloperService() {
        System.out.println("DeveloperServiceImpl");
        if (developerService == null) {
            synchronized (DeveloperServiceImpl.class) {
                if (developerService == null) {
                    developerService = new DeveloperServiceImpl();
                }
            }
        }
        return developerService;
    }

    @Override
    public Developer create(Developer e) {
        return CRUD_REPOSITORY.create(e);
    }

    @Override
    public Developer update(Developer e) {
        return CRUD_REPOSITORY.update(e);
    }

    @Override
    public void deleteById(Long id) {
        CRUD_REPOSITORY.deleteById(id);
    }

    @Override
    public Optional<Developer> findById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        return CRUD_REPOSITORY.findAll();
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
