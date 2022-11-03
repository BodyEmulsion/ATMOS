package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Settings;
import com.peltikhin.atmos.jpa.repositories.SettingsRepository;
import com.peltikhin.atmos.services.dto.SettingsDto;
import com.peltikhin.atmos.services.dto.UserDto;
import com.peltikhin.atmos.services.mappers.SettingsMapper;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class SettingsService {
    private final SettingsRepository settingsRepository;
    private final AuthService authService;
    private final SettingsMapper mapper;

    public SettingsService(SettingsRepository settingsRepository, AuthService authService, SettingsMapper mapper) {
        this.settingsRepository = settingsRepository;
        this.authService = authService;
        this.mapper = mapper;
    }

    public SettingsDto getSettings() {
        Settings settings = getSettingsOrCreateDefault();
        return mapper.toDto(settings);
    }

    public SettingsDto updateSettings(Time dayChangeTime) {
        Settings settings = getSettingsOrCreateDefault();
        settings.setDayChangeTime(dayChangeTime);
        return mapper.toDto(this.settingsRepository.save(settings));
    }

    private Settings getSettingsOrCreateDefault() {
        UserDto user = authService.getCurrentUser();
        Optional<Settings> result = settingsRepository.findByUserId(user.getId());
        if (result.isEmpty()) {
            Settings defaultSettings = Settings.builder()
                    .dayChangeTime(Time.valueOf(LocalTime.MIDNIGHT))
                    .userId(user.getId())
                    .build();
            result = Optional.of(settingsRepository.save(defaultSettings));
        }
        return result.get();
    }
}
