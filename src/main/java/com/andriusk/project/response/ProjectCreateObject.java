package com.andriusk.project.response;

import lombok.Data;

@Data
public class ProjectCreateObject {

    private String projectName;
    private String projectDescription;
    private String projectManager;
    private String projectStatus;
    private String projectDeadline;

}
