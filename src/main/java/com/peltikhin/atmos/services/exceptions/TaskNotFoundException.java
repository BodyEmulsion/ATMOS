package com.peltikhin.atmos.services.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super(String.format("Task with id=%d not found", id));
    }
}
