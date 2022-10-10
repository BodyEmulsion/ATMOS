package com.peltikhin.atmos.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not enough authorities to do this")
public class NotEnoughAuthoritiesException extends RuntimeException {

    public NotEnoughAuthoritiesException(String message) {
        super(message);
    }
}
