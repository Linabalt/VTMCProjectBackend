package com.andriusk.project.entity;

import com.andriusk.project.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    private String projectName;

    private String projectDescription;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @CreatedDate
    private LocalDateTime projectCreatedOn;

    @LastModifiedDate
    private LocalDateTime projectModifiedOn;

    private LocalDateTime projectDeadline;

//    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    // For testing purposes only
    public Project(String projectName, String projectDescription, ProjectStatus projectStatus) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
