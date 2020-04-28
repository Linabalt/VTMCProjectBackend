package com.andriusk.project.response;

import lombok.Data;

@Data
public class TaskCreateObject {
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private String TaskStatus;
    private String taskDeadline;
    private String taskRole;
    private String taskStory;
    private String taskPurpose;
    private String taskAcceptance;
}
