package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Settings;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.SettingsRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class SettingsService {
    private final SettingsRepository settingsRepository;
    private final AuthService authService;

    public SettingsService(SettingsRepository settingsRepository, AuthService authService) {
        this.settingsRepository = settingsRepository;
        this.authService = authService;
    }

    private static Settings createDefaultSettings() {
        return Settings.builder()
                .dayChangeTime(Time.valueOf(LocalTime.MIDNIGHT))
                .build();
    }

    public Settings getSettings() {
        User user = authService.getCurrentUser();
        Optional<Settings> settings = settingsRepository.findByUser(user);
        if (settings.isEmpty()) {
            Settings defaultSettings = createDefaultSettings();
            defaultSettings.setUser(user);
            settings = Optional.of(settingsRepository.save(defaultSettings));
        }
        return settings.get();
    }

    public Settings updateSettings(Time dayChangeTime) {
        Settings settings = getSettings();
        settings.setDayChangeTime(dayChangeTime);
        return this.settingsRepository.save(settings);
    }
}
