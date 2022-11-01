package com.peltikhin.atmos.services;

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

    public Notification createNotification(Long taskId, Date time) {
        Notification notification = Notification.builder()
                .task(taskService.getTaskById(taskId))
                .time(time)
                .build();
        return this.notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Date time, Long taskId) {
        Notification notification = getNotification(id);
        notification.setTime(time);
        if (!taskId.equals(notification.getTaskId())) {
            notification.setTask(taskService.getTaskById(taskId));
        }
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.delete(getNotification(id));
    }
}
