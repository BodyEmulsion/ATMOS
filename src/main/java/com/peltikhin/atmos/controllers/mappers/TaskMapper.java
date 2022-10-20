package com.peltikhin.atmos.controllers.mappers;

import com.peltikhin.atmos.controllers.dto.TaskDto;
import com.peltikhin.atmos.jpa.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TaskMapper {
    TaskDto toDTO(Task task);
}
