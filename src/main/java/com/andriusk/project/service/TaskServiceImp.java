package com.andriusk.project.service;

import com.andriusk.project.entity.Project;
import com.andriusk.project.entity.Task;
import com.andriusk.project.repository.ProjectRepository;
import com.andriusk.project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        projectRepository.findById(projectId).get().add(task);
	}

	@Override
	public List<Task> getAllTasks(Long projectId) {
		return projectRepository.findById(projectId).get().getTasks();
//        return projectRepository.viewAllTasks(projectId);
	}

	@Override
	public Task retrieveIndividualTask(Long projectID, Long taskID) {

		return projectRepository.findById(projectID).get().getTasks().stream().filter(t -> t.getTaskId().equals(taskID))
				.findFirst().get();

	}

	@Override
	public void deleteTaskByID(Long projectID, Long taskID) {

		taskRepository.deleteById(taskID);

//		 nelabai supratau kodel situo pagrindu neveikia,
//		 skamba patikimiau, randa projekto ID pasiema lista,
//		 ir tada removina. Bet nedirba sitaip.
//		
//		 projectRepository.findById(projectID).get().getTasks().remove(retrieveIndividualTask(projectID, taskID));

	}

	@Override
	public void editTask(Long projectID, Long taskID, String taskName, String taskDescription) {

		Task taskname = retrieveIndividualTask(projectID, taskID);
		taskname.setTaskDescription(taskDescription);
		taskname.setTaskName(taskName);
		taskRepository.save(taskname);

	}
}
