package org.homework.repository;

import org.homework.model.Developer;
import java.util.List;

public interface DeveloperCrudRepository extends CrudRepository<Developer,Long> {

    Object getSumSalariesDevelopersOfOneProject(Long projectId);

    List<Developer> getDevelopersFromOneProject(Long projectId);

    List<Developer> getDevelopersByActivity(String nameActivity);

    List<Developer> getDevelopersByLevel(String nameLevel);
}
