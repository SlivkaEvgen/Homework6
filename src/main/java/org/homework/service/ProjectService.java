package org.homework.service;

import org.homework.repository.ProjectCrudRepository;

import java.util.List;

public interface ProjectService extends ProjectCrudRepository {

    List<String> getListProjectsWithDate();
}
