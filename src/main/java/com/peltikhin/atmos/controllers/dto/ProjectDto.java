package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.jpa.models.Project;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectDto {
    private Long id;
    private String name;
    private Long userId;

    //TODO move it to mapper or somewhere like that
    public ProjectDto(Project project) {
        this(project.getId(), project.getName(), project.getUser().getId());
    }
}
