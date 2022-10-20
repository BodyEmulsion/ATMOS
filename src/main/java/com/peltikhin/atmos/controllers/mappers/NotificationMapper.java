package com.peltikhin.atmos.controllers.mappers;

import com.peltikhin.atmos.controllers.dto.NotificationDto;
import com.peltikhin.atmos.jpa.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface NotificationMapper {
    @Mapping(source = "task.id", target = "taskId")
    NotificationDto toDto(Notification notification);
}
