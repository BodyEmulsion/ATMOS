package com.peltikhin.atmos.services.dto;

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
    private Date created;

    public boolean isPlanned(){
        return blockId != null;
    }
}
