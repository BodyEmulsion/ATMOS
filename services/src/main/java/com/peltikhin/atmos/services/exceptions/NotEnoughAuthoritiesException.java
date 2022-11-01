package com.peltikhin.atmos.services.exceptions;

//TODO write exception handler in controllers
public class NotEnoughAuthoritiesException extends RuntimeException {

    public NotEnoughAuthoritiesException(String message) {
        super(message);
    }
}
