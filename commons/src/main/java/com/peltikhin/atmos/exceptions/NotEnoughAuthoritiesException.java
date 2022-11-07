package com.peltikhin.atmos.exceptions;


//TODO write exception handler in controllers
//@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not enough authorities to do this")
public class NotEnoughAuthoritiesException extends RuntimeException {

    public NotEnoughAuthoritiesException(String message) {
        super(message);
    }
}
