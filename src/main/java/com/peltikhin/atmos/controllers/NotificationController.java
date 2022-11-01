package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.NotificationDto;
import com.peltikhin.atmos.controllers.mappers.NotificationMapper;
import com.peltikhin.atmos.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    public NotificationController(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<NotificationDto>> getNotifications(@RequestParam Long taskId){
        Collection<NotificationDto> notifications = this.notificationService.getNotificationsByTaskId(taskId).stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotification(@PathVariable Long id){
        NotificationDto notification = notificationMapper.toDto(this.notificationService.getNotification(id));
        return ResponseEntity.ok(notification);
    }

    //TODO Make sure that method's name is normal, and consider path
    @GetMapping("/date")
    public ResponseEntity<Collection<NotificationDto>> getNearNotifications(@RequestParam Long untilDate){
        //TODO Rewrite it, and come up with timezones?
        Date before = Date.from(Instant.ofEpochMilli(untilDate));
        Collection<NotificationDto> notifications = this.notificationService.getNearNotifications(before).stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto){
        NotificationDto notification = notificationMapper.toDto(
                this.notificationService.createNotification(notificationDto.getTaskId(), notificationDto.getTime()));
        return ResponseEntity.ok(notification);
    }

    @PutMapping
    public ResponseEntity<NotificationDto> updateNotification(@RequestBody NotificationDto notificationDto){
        NotificationDto notification = notificationMapper.toDto(
                this.notificationService.updateNotification(notificationDto.getId(), notificationDto.getTime(), notificationDto.getTaskId()));
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id){
        this.notificationService.deleteNotification(id);
        return ResponseEntity.ok(null);
    }

}
