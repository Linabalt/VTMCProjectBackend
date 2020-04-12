package com.andriusk.project.controller;

import com.andriusk.project.entity.Task;
import com.andriusk.project.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "/projects/{projectId}")
@RestController
@RequestMapping(value = "/projects/{projectId}")
public class TaskController {

	@Autowired
	TaskService taskService;

	@GetMapping(value = "/tasks")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all tasks from a project", notes = "Returns a set of all tasks belonging to a project.")
	public List<Task> getTasks(@PathVariable Long projectId) {
		return taskService.getAllTasks(projectId);
	}

	@PostMapping(value = "/tasks")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Create a task", notes = "Creates a new task for the specified project.")
	public void createTask(@PathVariable Long projectId, @RequestBody Task task) {
		taskService.createTask(task, projectId);
	}

	@GetMapping(value = "/{oneTask}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get individual task from a project", notes = "Returns a task from all projects.")
	public Task retrieveIndividualTask(@PathVariable Long projectId, Long taskId) {
		return taskService.retrieveIndividualTask(projectId, taskId);
	}

	@DeleteMapping("/{taskID}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete a task", notes = "Deletes the specific task.")
	public void deleteProject(@PathVariable Long projectId, Long taskId) {
		taskService.deleteTaskByID(projectId, taskId);
	}

	@PostMapping(value = "/{taskID}/{taskName}/{taskDescription}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Edit specific task", notes = "Edits Task name and its description")
	public void editTask(@PathVariable Long projectId, Long taskId, String taskName, String taskDesc) {
		taskService.editTask(projectId, taskId, taskName, taskDesc);

	}

    //TODO
   /*
   Method for retrieving individual tasks.
   Method for deleting a specific task.
   Method for updating a specific task. Check if updateOn field updates properly when a task is edited.
   */
}
