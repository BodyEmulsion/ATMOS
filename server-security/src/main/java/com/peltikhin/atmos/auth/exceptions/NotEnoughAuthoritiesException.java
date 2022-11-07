package com.peltikhin.atmos.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO write exception handler in controllers
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not enough authorities to do this")
public class NotEnoughAuthoritiesException extends AccessDeniedException {

    public NotEnoughAuthoritiesException(String message) {
        super(message);
    }
}
