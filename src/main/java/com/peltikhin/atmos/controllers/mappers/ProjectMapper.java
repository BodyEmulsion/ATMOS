package com.peltikhin.atmos.controllers.mappers;

import com.peltikhin.atmos.controllers.dto.ProjectDto;
import com.peltikhin.atmos.jpa.models.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProjectMapper {
    @Mapping(source = "user.id", target = "userId")
    ProjectDto toDto(Project project);
}
