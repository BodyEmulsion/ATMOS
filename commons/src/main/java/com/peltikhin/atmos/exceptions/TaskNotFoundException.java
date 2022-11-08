package com.peltikhin.atmos.exceptions;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(Long id) {
        super(String.format("Task with id=%d not found", id));
    }
}
