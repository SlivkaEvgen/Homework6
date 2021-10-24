package org.homework.repository;

import lombok.SneakyThrows;
import org.homework.model.Developer;
import org.homework.util.DatabaseConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeveloperCrudRepositoryImpl extends CrudRepositoryImpl<Developer, Long> implements Serializable, DeveloperCrudRepository {

    private static final long serialVersionUID = 10000000024L;
    private final Connection CONNECTION;
    private static DeveloperCrudRepositoryImpl developerCrudRepository;

    private DeveloperCrudRepositoryImpl(Class<Developer> modelClass) {
        super(modelClass);
        this.CONNECTION = DatabaseConnection.getInstance().getConnection();
    }

    public static DeveloperCrudRepositoryImpl getDeveloperService() {
        System.out.println("DeveloperServiceImpl");
        if (developerCrudRepository == null) {
            synchronized (DeveloperCrudRepositoryImpl.class) {
                if (developerCrudRepository == null) {
                    developerCrudRepository = new DeveloperCrudRepositoryImpl(Developer.class);
                }
            }
        }
        return developerCrudRepository;
    }

    @SneakyThrows
    @Override
    public Object getSumSalariesDevelopersOfOneProject(Long projectId) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder(" Not found  Project by ID = " + projectId);
        try (ResultSet resultSet = CONNECTION.createStatement().executeQuery(
                "SELECT projects.id AS projectID, projects.name AS projectName, SUM(developers.salary) AS sumSalaries FROM developers_projects " +
                        "inner join developers on developers_projects.developer_id = developers.id " +
                        "inner join projects on developers_projects.project_id = projects.id " +
                        "WHERE projects.id=" + projectId)) {
            while (resultSet.next()) {
                if (resultSet.getLong("projectID") != 0) {
                    stringBuilder = new StringBuilder(resultSet.getString("projectName") + " - " + resultSet.getString("sumSalaries"));
                }
            }
        } catch (NumberFormatException r) {
            return r;
        }
        return stringBuilder.toString();
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "SELECT * FROM developers_projects "
                        + "inner join developers on developers_projects.developer_id = developers.id "
                        + "inner join projects on developers_projects.project_id = projects.id "
                        + "WHERE projects.id=" + projectId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            developersList.add(buildDeveloper(resultSet));
        }
        return developersList;
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        final List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM developers_skills "
                + "inner join developers on developers_skills.developer_id = developers.id "
                + "inner join skills on developers_skills.skill_id = skills.id "
                + "WHERE skills.activity=?");
        preparedStatement.setString(1, nameActivity);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            developersList.add(buildDeveloper(resultSet));
        }
        return developersList;
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        final List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM developers_skills "
                + "inner join developers on developers_skills.developer_id = developers.id "
                + "inner join skills on developers_skills.skill_id = skills.id "
                + "WHERE skills.level=?");
        preparedStatement.setString(1, nameLevel);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            developersList.add(buildDeveloper(resultSet));
        }
        return developersList;
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
