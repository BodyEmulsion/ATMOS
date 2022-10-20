package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.SettingsDto;
import com.peltikhin.atmos.controllers.mappers.SettingsMapper;
import com.peltikhin.atmos.services.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    private final SettingsService settingsService;
    private final SettingsMapper settingsMapper;

    public SettingsController(SettingsService settingsService, SettingsMapper settingsMapper) {
        this.settingsService = settingsService;
        this.settingsMapper = settingsMapper;
    }

    @GetMapping
    public ResponseEntity<SettingsDto> getSettings() {
        SettingsDto settings = settingsMapper.toDto(this.settingsService.getSettings());
        return ResponseEntity.ok(settings);
    }

    @PutMapping
    public ResponseEntity<SettingsDto> updateSettings(@RequestBody SettingsDto settingsDto) {
        SettingsDto settings = settingsMapper.toDto(this.settingsService.updateSettings(settingsDto));
        return ResponseEntity.ok(settings);
    }
}
