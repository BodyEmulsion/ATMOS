package com.peltikhin.atmos.handlers;

import com.peltikhin.atmos.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StorageExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Void> handleException(NotFoundException exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("error-type", "Requested entity not found");
        headers.add("error-message", exception.getMessage());
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
}
