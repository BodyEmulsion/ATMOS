package com.peltikhin.atmos.controllers.dto;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDto {
    private Long id;
    private Time dayChangeTime;
    private Long userId;
}
