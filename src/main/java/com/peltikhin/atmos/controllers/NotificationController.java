package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.services.NotificationService;
import com.peltikhin.atmos.services.dto.NotificationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<Collection<NotificationDto>> getNotifications(@RequestParam Long taskId) {
        Collection<NotificationDto> notifications = this.notificationService.getNotificationsByTaskId(taskId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotification(@PathVariable Long id) {
        NotificationDto notification = this.notificationService.getNotification(id);
        return ResponseEntity.ok(notification);
    }

    //TODO Make sure that method's name is normal, and consider path
    @GetMapping("/date")
    public ResponseEntity<Collection<NotificationDto>> getNearNotifications(@RequestParam Long untilDate) {
        //TODO Rewrite it, and come up with timezones?
        Date before = Date.from(Instant.ofEpochMilli(untilDate));
        Collection<NotificationDto> notifications = this.notificationService.getNearNotifications(before);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto notification = this.notificationService.createNotification(notificationDto);
        return ResponseEntity.ok(notification);
    }

    @PutMapping
    public ResponseEntity<NotificationDto> updateNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto notification = this.notificationService.updateNotification(notificationDto);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        this.notificationService.deleteNotification(id);
        return ResponseEntity.ok(null);
    }

}
