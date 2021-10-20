package org.homework.repository;

import org.homework.model.Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectCrudRepositoryImpl implements ProjectCrudRepository, Serializable {

    private static final long serialVersionUID = 10000000023L;
    private final CrudRepository<Project, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Project.class);
    private static ProjectCrudRepositoryImpl projectRepository;

    public static ProjectCrudRepositoryImpl getProjectCrudRepository() {
        System.out.println("ProjectCrudRepositoryImpl");
        if (projectRepository == null) {
            synchronized (ProjectCrudRepositoryImpl.class) {
                if (projectRepository == null) {
                    projectRepository = new ProjectCrudRepositoryImpl();
                }
            }
        }
        return projectRepository;
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<String> projectWithDate = new ArrayList<>();
        for (Project project : findAll()) {
            projectWithDate.add("Project " + project.getName() + " has " +
                    DeveloperCrudRepositoryImpl.getDeveloperCrudRepository()
                            .getDevelopersFromOneProject(project.getId()).size()
                    + " developers, sight date: " +
                    project.getFirstDate());
        }
        return projectWithDate;
    }

    @Override
    public Project create(Project e) {
        return CRUD_REPOSITORY_JDBC.create(e);
    }

    @Override
    public Project update(Project e) {
        return CRUD_REPOSITORY_JDBC.update(e);
    }

    @Override
    public void deleteById(Long id) {
        CRUD_REPOSITORY_JDBC.deleteById(id);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }
}
