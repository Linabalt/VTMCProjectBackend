package com.andriusk.project.service;

import com.andriusk.project.entity.Task;

import java.util.List;

public interface TaskService {
    void createTask(Task task, Long projectId);

    List<Task> getAllTasks(Long projectId);

    Task getTask(Long taskId);

    void deleteTaskById(Long taskId);

    void editTask(Long projectID, Task task);

    List<Task> findByTaskName(String searchTerm);

    void createTask(String payload, long projectId);

//    TaskProgress retrieveProgress(Long projectId);
}
