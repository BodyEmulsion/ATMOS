package com.peltikhin.atmos.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something with authentication data goes completely wrong")
public class NonExistentUserDoingThings extends RuntimeException {
    public NonExistentUserDoingThings(String message) {
        super(message);
    }
}
