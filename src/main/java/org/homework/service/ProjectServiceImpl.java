package org.homework.service;

import org.homework.model.Project;
import org.homework.repository.ProjectCrudRepository;
import org.homework.repository.ProjectCrudRepositoryImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService, Serializable {

    private final ProjectCrudRepository CRUD_REPOSITORY = ProjectCrudRepositoryImpl.getProjectCrudRepository();
    private static ProjectServiceImpl projectService;

    public static ProjectServiceImpl getProjectService() {
        System.out.println("ProjectServiceImpl");
        if (projectService == null) {
            synchronized (ProjectServiceImpl.class) {
                if (projectService == null) {
                    projectService = new ProjectServiceImpl();
                }
            }
        }
        return projectService;
    }

    @Override
    public Project create(Project e) {
        return CRUD_REPOSITORY.create(e);
    }

    @Override
    public Project update(Project e) {
        return CRUD_REPOSITORY.update(e);
    }

    @Override
    public void deleteById(Long id) {
        CRUD_REPOSITORY.deleteById(id);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return CRUD_REPOSITORY.getListProjectsWithDate();
    }
}
