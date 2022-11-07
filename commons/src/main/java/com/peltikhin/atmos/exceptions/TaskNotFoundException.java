package com.peltikhin.atmos.exceptions;

//TODO write exception handler in controllers
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super(String.format("Task with id=%d not found", id));
    }
}
