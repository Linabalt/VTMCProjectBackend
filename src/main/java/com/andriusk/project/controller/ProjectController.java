package com.andriusk.project.controller;

import com.andriusk.project.entity.Project;
import com.andriusk.project.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "/projects")
@RestController
@RequestMapping(value = "/projects")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all projects", notes = "Returns a list of all projects.")
    public List<Project> getProjects() {
        return projectService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create a project", notes = "Creates a new project.")
    public void createProject(@RequestBody Project project) {
        projectService.save(project);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a project", notes = "Deletes the specified project.")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProjectByID(projectId);
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get project by ID", notes = "Returns a project by the specified ID.")
    public Project getProjectByID(@PathVariable Long projectId) {
        return projectService.getProjectByID(projectId);
    }

}