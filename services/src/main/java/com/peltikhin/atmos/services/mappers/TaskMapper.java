package com.peltikhin.atmos.services.mappers;

import com.peltikhin.atmos.services.dto.TaskDto;
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
