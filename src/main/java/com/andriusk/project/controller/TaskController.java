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

    //TODO
   /*
   Method for retrieving individual tasks.
   Method for deleting a specific task.
   Method for updating a specific task. Check if updateOn field updates properly when a task is edited.
   Method for searching for a task by it's name.
   */
}
