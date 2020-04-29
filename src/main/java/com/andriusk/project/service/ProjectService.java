package com.andriusk.project.service;

import com.andriusk.project.entity.Project;
import com.andriusk.project.response.FullProjectInfo;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    Project getByProjectName(String projectName);

    void save(Project project);

    void createProject(String payload);

    Project findById(Long id);

    void deleteProjectById(Long id);

    Project getProjectById(Long id);

    List<Project> findByProjectName(String projectName);

    List<FullProjectInfo> retrieveFullInfo();

    FullProjectInfo retrieveFullInfoIndividual(Long projectId);
}
