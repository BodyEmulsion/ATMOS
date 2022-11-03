package com.peltikhin.atmos.services.mappers;

import com.peltikhin.atmos.services.dto.NotificationDto;
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
