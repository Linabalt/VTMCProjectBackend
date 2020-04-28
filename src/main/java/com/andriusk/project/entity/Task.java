package com.andriusk.project.entity;

import com.andriusk.project.enums.Priority;
import com.andriusk.project.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    private String taskName;

    private String taskDescription;

    @ManyToOne (fetch = FetchType.LAZY)  // (cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    @JsonIgnore
    private Project project;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private Priority taskPriority;

    @CreatedDate
    private LocalDateTime taskCreatedOn;

    @LastModifiedDate
    private LocalDateTime taskModifiedOn;

    private LocalDateTime taskDeadline;

    private String taskRole;

    private String taskStory;

    private String taskPurpose;

    private String taskAcceptance;

    // For testing only
    public Task(String taskName, String taskDescription, TaskStatus taskStatus, Priority taskPriority) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }

    public Task(String taskName, String taskDescription, TaskStatus taskStatus, Priority taskPriority, LocalDateTime taskDeadline, String taskRole, String taskStory, String taskPurpose, String taskAcceptance) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.taskDeadline = taskDeadline;
        this.taskRole = taskRole;
        this.taskStory = taskStory;
        this.taskPurpose = taskPurpose;
        this.taskAcceptance = taskAcceptance;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
