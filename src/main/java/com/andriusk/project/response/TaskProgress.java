package com.andriusk.project.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskProgress {
    private Long projectId;
    private Long totalTasks;
    private Long completeTasks;
}
