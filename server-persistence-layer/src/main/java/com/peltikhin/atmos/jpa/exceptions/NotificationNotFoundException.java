package com.peltikhin.atmos.jpa.exceptions;

//TODO write exception handler in controllers
public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(Long id) {
        super(String.format("Project with id=%d not found", id));
    }
}
