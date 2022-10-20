package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.SettingsDto;
import com.peltikhin.atmos.services.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public ResponseEntity<SettingsDto> getSettings() {
        SettingsDto settings = new SettingsDto(this.settingsService.getSettings());
        return ResponseEntity.ok(settings);
    }

    @PutMapping
    public ResponseEntity<SettingsDto> updateSettings(@RequestBody SettingsDto settingsDto) {
        SettingsDto settings = new SettingsDto(this.settingsService.updateSettings(settingsDto));
        return ResponseEntity.ok(settings);
    }
}
