package com.peltikhin.atmos.exceptions;

public class ProjectNotFoundException extends NotFoundException {
    public ProjectNotFoundException(Long id) {
        super(String.format("Project with id=%d not found", id));
    }
}
