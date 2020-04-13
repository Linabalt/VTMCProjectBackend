package com.andriusk.project.service;

import com.andriusk.project.entity.Project;
import com.andriusk.project.entity.Task;
import com.andriusk.project.repository.ProjectRepository;
import com.andriusk.project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void createTask(Task task, Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        task.setProject(project);
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks(Long projectId) {
        return projectRepository.findById(projectId).get().getTasks();
    }
//
//    @Override
//    public Task findById(Long taskId) {
//        return taskRepository.findById(taskId).get();
//    }

    @Override
    public List<Task> findByTaskName(String taskName) {
        Pattern pattern = Pattern.compile( "\\b(" + taskName + ".*)", Pattern.CASE_INSENSITIVE);
        return taskRepository.findAll().stream().filter(project -> pattern.matcher(project.getTaskName()).find()).collect(Collectors.toList());
    }
}
