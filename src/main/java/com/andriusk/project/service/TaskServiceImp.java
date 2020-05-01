package com.andriusk.project.service;

import com.andriusk.project.entity.Project;
import com.andriusk.project.entity.Task;
import com.andriusk.project.enums.Priority;
import com.andriusk.project.enums.TaskStatus;
import com.andriusk.project.repository.ProjectRepository;
import com.andriusk.project.repository.TaskRepository;
import com.andriusk.project.response.TaskCreateObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void createTask(Task task, Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        task.setProject(project);
        taskRepository.save(task);
    }

    @Override
    public void createTask(String payload, long projectId) {
        try {
            TaskCreateObject projectCreateObject = objectMapper.readValue(payload, TaskCreateObject.class);

            LocalDateTime deadline = LocalDateTime.of(LocalDate.parse(projectCreateObject.getTaskDeadline()), LocalTime.now());

            Priority priority;
            if (projectCreateObject.getTaskPriority().equals("HIGH")) {
                priority = Priority.HIGH;
            } else if (projectCreateObject.getTaskPriority().equals("MEDIUM")) {
                priority = Priority.MEDIUM;
            } else {
                priority = Priority.LOW;
            }

            TaskStatus status;
            if (projectCreateObject.getTaskStatus().equals("NOT_STARTED")) {
                status = TaskStatus.NOT_STARTED;
            } else if (projectCreateObject.getTaskStatus().equals("IN_PROGRESS")) {
                status = TaskStatus.IN_PROGRESS;
            } else if (projectCreateObject.getTaskStatus().equals("COMPLETE")) {
                status = TaskStatus.COMPLETE;
            } else {
                status = TaskStatus.CANCELED;
            }

            Task newTask = new Task(projectCreateObject.getTaskName(), projectCreateObject.getTaskDescription(), status, priority, deadline,
                    projectCreateObject.getTaskStory());

            createTask(newTask, projectId);


        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Failed to save a task from received data.");
        }
    }

    @Override
    public List<Task> getAllTasks(Long projectId) {
        return projectRepository.findById(projectId).get().getTasks();
    }

    @Override
    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).get();
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void editTask(Long taskId, TaskCreateObject task) {
        Task editedTask = getTask(taskId);
        editedTask.setTaskDescription(task.getTaskDescription());
        editedTask.setTaskName(task.getTaskName());
        editedTask.setTaskDeadline(LocalDateTime.of(LocalDate.parse(task.getTaskDeadline()), LocalTime.now()));

        switch (task.getTaskStatus()) {
            case "NOT_STARTED":
                editedTask.setTaskStatus(TaskStatus.NOT_STARTED);
                break;
            case "IN_PROGRESS":
                editedTask.setTaskStatus(TaskStatus.IN_PROGRESS);
                break;
            case "COMPLETE":
                editedTask.setTaskStatus(TaskStatus.COMPLETE);
                break;
            default:
                editedTask.setTaskStatus(TaskStatus.CANCELED);
                break;
        }

        switch (task.getTaskPriority()) {
            case "LOW":
                editedTask.setTaskPriority(Priority.LOW);
                break;
            case "MEDIUM":
                editedTask.setTaskPriority(Priority.MEDIUM);
                break;
            default:
                editedTask.setTaskPriority(Priority.HIGH);
                break;
        }

        taskRepository.save(editedTask);
    }

    @Override
    public List<Task> findByTaskName(String searchTerm) {
        Pattern pattern = Pattern.compile("\\b(" + searchTerm + ".*)", Pattern.CASE_INSENSITIVE);
        return taskRepository.findAll().stream().filter(task -> pattern.matcher(task.getTaskName()).find()).collect(Collectors.toList());
    }
}
