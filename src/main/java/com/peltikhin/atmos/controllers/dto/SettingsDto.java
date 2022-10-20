package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.jpa.models.Settings;
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

    public SettingsDto(Settings settings) {
        this.id = settings.getId();
        this.dayChangeTime = settings.getDayChangeTime();
        this.userId = settings.getUser().getId();
    }
}
