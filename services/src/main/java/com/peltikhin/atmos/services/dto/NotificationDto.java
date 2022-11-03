package com.peltikhin.atmos.services.dto;

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
}
