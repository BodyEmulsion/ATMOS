package com.peltikhin.atmos.jpa.exceptions;

//TODO write exception handler in controllers
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super(String.format("Project with id=%d not found", id));
    }
}
