package org.homework.repository;

import lombok.SneakyThrows;
import org.homework.model.Developer;
import org.homework.util.DatabaseConnection;
import org.homework.util.PropertiesLoader;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperCrudRepositoryImpl implements DeveloperCrudRepository, Serializable {

    private static final long serialVersionUID = 10000000024L;
    private final Connection CONNECTION = DatabaseConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperties("db.schemaName");
    private final CrudRepository<Developer, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Developer.class);
    private static DeveloperCrudRepositoryImpl developerCrudRepository;

    public static DeveloperCrudRepositoryImpl getDeveloperCrudRepository() {
        System.out.println("DeveloperCrudRepositoryImpl");
        if (developerCrudRepository == null) {
            synchronized (DeveloperCrudRepositoryImpl.class) {
                if (developerCrudRepository == null) {
                    developerCrudRepository = new DeveloperCrudRepositoryImpl();
                }
            }
        }
        return developerCrudRepository;
    }

    @SneakyThrows
    @Override
    public Object getSumSalariesDevelopersOfOneProject(Long projectId) {
        String result = " Not found  Project by ID = " + projectId;
        try (ResultSet resultSet = CONNECTION.createStatement().executeQuery(
                "SELECT projects.id AS projectID, projects.name AS projectName, "
                        + "SUM(developers.salary) AS sumSalaries FROM developers_projects "
                        + "inner join developers on developers_projects.developer_id = developers.id "
                        + "inner join projects on developers_projects.project_id = projects.id "
                        + "WHERE projects.id=" + projectId)) {
            while (resultSet.next()) {
                if (resultSet.getLong("projectID") == 0) {
                    break;
                }
                result = resultSet.getString("projectName") + " - " + resultSet.getString("sumSalaries");
            }
        } catch (NumberFormatException r) {
            System.out.println(result + r);
        }
        return result;
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM " + SCHEMA_NAME + ".developers_projects "
                + "inner join " + SCHEMA_NAME + ".developers on " + SCHEMA_NAME + ".developers_projects.developer_id = " + SCHEMA_NAME + ".developers.id "
                + "inner join " + SCHEMA_NAME + ".projects on " + SCHEMA_NAME + ".developers_projects.project_id = " + SCHEMA_NAME + ".projects.id "
                + "WHERE " + SCHEMA_NAME + ".projects.id=?");

        preparedStatement.setLong(1, projectId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Developer developer = buildDeveloper(resultSet);
            developersList.add(developer);
        }
        return developersList;
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        final List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM " + SCHEMA_NAME + ".developers_skills "
                + "inner join " + SCHEMA_NAME + ".developers on " + SCHEMA_NAME + ".developers_skills.developer_id = " + SCHEMA_NAME + ".developers.id "
                + "inner join " + SCHEMA_NAME + ".skills on " + SCHEMA_NAME + ".developers_skills.skill_id = " + SCHEMA_NAME + ".skills.id "
                + "WHERE " + SCHEMA_NAME + ".skills.activity=?");

        preparedStatement.setString(1, nameActivity);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Developer developer = buildDeveloper(resultSet);
            developersList.add(developer);
        }
        return developersList;
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        final List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM " + SCHEMA_NAME + ".developers_skills "
                + "inner join " + SCHEMA_NAME + ".developers on " + SCHEMA_NAME + ".developers_skills.developer_id = " + SCHEMA_NAME + ".developers.id "
                + "inner join " + SCHEMA_NAME + ".skills on " + SCHEMA_NAME + ".developers_skills.skill_id = " + SCHEMA_NAME + ".skills.id "
                + "WHERE " + SCHEMA_NAME + ".skills.level=?");

        preparedStatement.setString(1, nameLevel);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Developer developer = buildDeveloper(resultSet);
            developersList.add(developer);
        }
        return developersList;
    }

    @Override
    public Developer create(Developer e) {
        return CRUD_REPOSITORY_JDBC.create(e);
    }

    @Override
    public Developer update(Developer e) {
        return CRUD_REPOSITORY_JDBC.update(e);
    }

    @Override
    public void deleteById(Long id) {
        CRUD_REPOSITORY_JDBC.deleteById(id);
    }

    @Override
    public Optional<Developer> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @SneakyThrows
    private Developer buildDeveloper(ResultSet resultSet) {
        return Developer.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .age(resultSet.getLong("age"))
                .gender(resultSet.getString("gender"))
                .email(resultSet.getString("email"))
                .companyId(resultSet.getLong("company_id"))
                .salary(resultSet.getLong("salary"))
                .build();
    }
}
