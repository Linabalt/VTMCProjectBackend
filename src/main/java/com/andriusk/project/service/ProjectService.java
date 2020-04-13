package com.andriusk.project.service;

import com.andriusk.project.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    void save(Project project);

    Project findById(Long id);

    void deleteProjectById(Long id);

    Project getProjectById(Long id);

    List<Project> findByProjectName(String projectName);
}
