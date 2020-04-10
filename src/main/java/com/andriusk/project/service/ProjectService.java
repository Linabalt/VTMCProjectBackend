package com.andriusk.project.service;

import com.andriusk.project.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    void save(Project project);
    Project findById(Long id);
    void deleteProjectByID(Long id);
    Project getProjectByID(Long id);
}
