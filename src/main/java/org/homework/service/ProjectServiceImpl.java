package org.homework.service;

import org.homework.model.Project;
import org.homework.repository.ProjectCrudRepository;
import org.homework.repository.ProjectCrudRepositoryImpl;

import java.util.List;

public class ProjectServiceImpl extends CrudServiceImpl<Project,Long> implements ProjectService {

    private final ProjectCrudRepository CRUD_REPOSITORY;
    private static ProjectServiceImpl projectService;

    private ProjectServiceImpl(Class<Project> modelClass) {
        super(modelClass);
        this.CRUD_REPOSITORY = ProjectCrudRepositoryImpl.getDeveloperService();
    }

    public static ProjectServiceImpl getProjectService() {
        if (projectService == null) {
            synchronized (ProjectServiceImpl.class) {
                if (projectService == null) {
                    projectService = new ProjectServiceImpl(Project.class);
                }
            }
        }
        return projectService;
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return CRUD_REPOSITORY.getListProjectsWithDate();
    }
}
