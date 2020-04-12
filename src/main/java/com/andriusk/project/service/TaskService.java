package com.andriusk.project.service;

import com.andriusk.project.entity.Task;

import java.util.List;

public interface TaskService {
	void createTask(Task task, Long projectId);

	List<Task> getAllTasks(Long projectId);

	Task retrieveIndividualTask(Long projectId, Long taskId);

	void deleteTaskByID(Long projectId, Long taskId);

	void editTask(Long projectId, Long taskId, String taskName, String taskDescription);
}
