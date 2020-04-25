package com.andriusk.project.response;

import com.andriusk.project.entity.Project;
import com.andriusk.project.enums.ProjectStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FullProjectInfo {

    private Long projectId;
    private String projectName;
    private String projectDescription;
    private String projectManager;
    private String projectStatus;
    private LocalDateTime projectCreatedOn;
    private LocalDateTime projectModifiedOn;
    private LocalDateTime projectDeadline;
    private Long completeTasks;
    private Long totalTasks;

    public FullProjectInfo(Project project, Long completeTasks, Long totalTasks) {
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();
        this.projectDescription = project.getProjectDescription();
        this.projectManager = project.getProjectManager();
        if (project.getProjectStatus() == ProjectStatus.IN_PROGRESS) {
            this.projectStatus = "In progress";
        } else if (project.getProjectStatus() == ProjectStatus.NOT_STARTED){
            this.projectStatus = "Not started";
        } else {
            this.projectStatus = "Complete";
        }
        this.projectCreatedOn = project.getProjectCreatedOn();
        this.projectModifiedOn = project.getProjectModifiedOn();
        this.projectDeadline = project.getProjectDeadline();
        this.completeTasks = completeTasks;
        this.totalTasks = totalTasks;
    }
}
