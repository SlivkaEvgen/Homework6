package org.homework.service;

import org.homework.model.Developer;
import org.homework.repository.DeveloperCrudRepository;

import java.util.List;

public interface DeveloperService extends DeveloperCrudRepository {

    Object getSumSalariesDevelopersOfOneProject(Long projectId);

    List<Developer> getDevelopersFromOneProject(Long projectId);

    List<Developer> getDevelopersByActivity(String nameActivity);

    List<Developer> getDevelopersByLevel(String nameLevel);
}
