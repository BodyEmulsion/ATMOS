package com.peltikhin.atmos.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such block")
public class BlockNotFoundException extends RuntimeException {
    public BlockNotFoundException(Long id) {
        super(String.format("Block with id=%d not found", id));
    }
}
