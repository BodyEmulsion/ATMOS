package com.peltikhin.atmos.handlers;

import com.peltikhin.atmos.exceptions.NotEnoughAuthoritiesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityExceptionHandler {
    //TODO: think about special Dto that will contain error information
    @ExceptionHandler(NotEnoughAuthoritiesException.class)
    public ResponseEntity<Void> handleException(NotEnoughAuthoritiesException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("error-type", "Not enough authorities");
        headers.add("error-message", exception.getMessage());
        return new ResponseEntity<>(headers, HttpStatus.UNAUTHORIZED);
    }
}
