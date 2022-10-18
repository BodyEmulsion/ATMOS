package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.jpa.models.Notification;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private Long taskId;
    private Date time;
    public NotificationDto(Notification notification){
        this.id = notification.getId();
        this.taskId = notification.getTask().getId();
        this.time = notification.getTime();
    }
}
