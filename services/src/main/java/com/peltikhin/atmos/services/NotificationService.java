package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Notification;
import com.peltikhin.atmos.jpa.repositories.NotificationRepository;
import com.peltikhin.atmos.services.dto.NotificationDto;
import com.peltikhin.atmos.services.dto.UserDto;
import com.peltikhin.atmos.services.mappers.NotificationMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final AuthService authService;
    private final NotificationMapper mapper;

    public NotificationService(NotificationRepository notificationRepository, AuthService authService, NotificationMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.authService = authService;
        this.mapper = mapper;
    }

    public List<NotificationDto> getNotificationsByTaskId(Long taskId) {
        return notificationRepository.findAllByTaskId(taskId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public NotificationDto getNotification(Long notificationId) {
        Notification notification = notificationRepository.findByIdOrError(notificationId);
        return mapper.toDto(notification);
    }

    public List<NotificationDto> getNearNotifications(Date before) {
        UserDto user = authService.getCurrentUser();
        Date after = Date.from(Instant.now());
        return notificationRepository.findAllByTask_Project_User_IdAndTimeBetween(user.getId(), after, before).stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .taskId(notificationDto.getTaskId())
                .time(notificationDto.getTime())
                .build();
        return mapper.toDto(this.notificationRepository.save(notification));
    }

    public NotificationDto updateNotification(NotificationDto notificationDto) {
        Notification notification = this.notificationRepository.findByIdOrError(notificationDto.getId());
        notification.setTime(notificationDto.getTime());
        notification.setTaskId(notificationDto.getTaskId());
        return mapper.toDto(notificationRepository.save(notification));
    }

    public void deleteNotification(Long id) {
        Notification notification = this.notificationRepository.findByIdOrError(id);
        notificationRepository.delete(notification);
    }
}
