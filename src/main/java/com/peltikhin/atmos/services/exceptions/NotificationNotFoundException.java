package com.peltikhin.atmos.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such notification")
public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(Long id) {
        super(String.format("Project with id=%d not found", id));
    }
}
