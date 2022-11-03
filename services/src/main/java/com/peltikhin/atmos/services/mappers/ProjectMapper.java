package com.peltikhin.atmos.services.mappers;

import com.peltikhin.atmos.services.dto.ProjectDto;
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
