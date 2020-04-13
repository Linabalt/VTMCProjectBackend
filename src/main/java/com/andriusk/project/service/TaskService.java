package com.andriusk.project.service;

import com.andriusk.project.entity.Task;

import java.util.List;

public interface TaskService {
    void createTask(Task task, Long projectId);
    List<Task> getAllTasks(Long projectId);
//    Task findById(Long projectId);
    List<Task> findByTaskName(String taskName);
}
