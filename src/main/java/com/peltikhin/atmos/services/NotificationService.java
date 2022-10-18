package com.peltikhin.atmos.services;

import com.peltikhin.atmos.controllers.dto.NotificationDto;
import com.peltikhin.atmos.jpa.models.Notification;
import com.peltikhin.atmos.jpa.models.Task;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.NotificationRepository;
import com.peltikhin.atmos.services.exceptions.NotEnoughAuthoritiesException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final TaskService taskService;
    private final AuthService authService;

    public NotificationService(NotificationRepository notificationRepository, TaskService taskService, AuthService authService) {
        this.notificationRepository = notificationRepository;
        this.taskService = taskService;
        this.authService = authService;
    }

    public Collection<Notification> getNotificationsByTaskId(Long taskId) {
        return taskService.getTaskById(taskId).getNotifications();
    }

    public Notification getNotification(Long notificationId) {
        Notification notification = notificationRepository.findByIdOrError(notificationId);
        validateUserAuthority(notification);
        return notification;
    }

    private void validateUserAuthority(Notification notification) {
        User currentUser = authService.getCurrentUser();
        if (!isNotificationBelongToUser(notification, currentUser))
            throw new NotEnoughAuthoritiesException("Notification doesn't belong to user");
    }

    private static boolean isNotificationBelongToUser(Notification notification, User currentUser) {
        return notification.getTask().getProject().getUser().getId().equals(currentUser.getId());
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
        Notification notification = notificationRepository.findByIdOrError(notificationDto.getId());
        notification.setTime(notificationDto.getTime());
        //TODO Optimize it:
        Task task = taskService.getTaskById(notificationDto.getTaskId());
        notification.setTask(task);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        //TODO test it:
        notificationRepository.delete(this.getNotification(id));
    }
}
