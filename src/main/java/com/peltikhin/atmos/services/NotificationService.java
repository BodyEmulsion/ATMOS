package com.peltikhin.atmos.services;

import com.peltikhin.atmos.controllers.dto.NotificationDto;
import com.peltikhin.atmos.jpa.models.Notification;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final TaskService taskService;
    private final AuthService authService;
    private final ValidationService validationService;

    public NotificationService(NotificationRepository notificationRepository, TaskService taskService, AuthService authService, ValidationService validationService) {
        this.notificationRepository = notificationRepository;
        this.taskService = taskService;
        this.authService = authService;
        this.validationService = validationService;
    }

    public Collection<Notification> getNotificationsByTaskId(Long taskId) {
        return taskService.getTaskById(taskId).getNotifications();
    }

    public Notification getNotification(Long notificationId) {
        Notification notification = notificationRepository.findByIdOrError(notificationId);
        validationService.validateUserAuthority(notification);
        return notification;
    }

    public Collection<Notification> getNearNotifications(Date before) {
        User user = authService.getCurrentUser();
        Date after = Date.from(Instant.now());
        return notificationRepository.findAllByTask_Project_UserAndTimeBetween(user, after, before);
    }

    public Notification createNotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .task(taskService.getTaskById(notificationDto.getTaskId()))
                .time(notificationDto.getTime())
                .build();
        return this.notificationRepository.save(notification);
    }

    public Notification updateNotification(NotificationDto notificationDto) {
        Notification notification = getNotification(notificationDto.getId());
        notification.setTime(notificationDto.getTime());
        if (!notificationDto.getTaskId().equals(notification.getTaskId())) {
            notification.setTask(taskService.getTaskById(notificationDto.getTaskId()));
        }
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.delete(getNotification(id));
    }
}
