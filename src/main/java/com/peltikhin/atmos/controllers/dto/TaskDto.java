package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.jpa.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private Long projectId;
    private Long blockId;
    private Boolean planned;
    private Date created;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.projectId = task.getProjectId();
        this.blockId = task.getBlockId();
        this.planned = task.getPlanned();
        this.created = task.getCreated();
    }
}
