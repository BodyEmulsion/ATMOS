package com.peltikhin.atmos.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not enough authorities to do this")
public class NotEnoughAuthoritiesExceprion extends RuntimeException {

    public NotEnoughAuthoritiesExceprion(String message) {
        super(message);
    }
}
