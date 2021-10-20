package org.homework.repository;

import org.homework.model.Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectCrudRepositoryImpl extends CrudRepositoryImpl<Project,Long> implements  Serializable,ProjectCrudRepository {

    private static final long serialVersionUID = 10000000023L;
    private static ProjectCrudRepositoryImpl projectCrudRepository;

    private ProjectCrudRepositoryImpl(Class<Project> modelClass) {
        super(modelClass);
    }

    public static ProjectCrudRepositoryImpl getDeveloperService() {
        if (projectCrudRepository == null) {
            synchronized (ProjectCrudRepositoryImpl.class) {
                if (projectCrudRepository == null) {
                    projectCrudRepository = new ProjectCrudRepositoryImpl(Project.class);
                }
            }
        }
        return projectCrudRepository;
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<String> projectWithDate = new ArrayList<>();
        for (Project project : findAll()) {
            projectWithDate.add("Project " + project.getName() + " has " +
                     DeveloperCrudRepositoryImpl.getDeveloperService().getDevelopersFromOneProject(project.getId()).size()
                    + " developers, sight date: " +
                    project.getFirstDate());
        }
        return projectWithDate;
    }
}
