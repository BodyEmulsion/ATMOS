package com.peltikhin.atmos.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such project")
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super(String.format("Project with id=%d not found", id));
    }
}
