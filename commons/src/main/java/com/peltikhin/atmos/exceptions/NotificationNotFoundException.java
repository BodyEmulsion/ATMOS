package com.peltikhin.atmos.exceptions;

public class NotificationNotFoundException extends NotFoundException {
    public NotificationNotFoundException(Long id) {
        super(String.format("Project with id=%d not found", id));
    }
}
