package com.peltikhin.atmos.services.dto;

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
}
