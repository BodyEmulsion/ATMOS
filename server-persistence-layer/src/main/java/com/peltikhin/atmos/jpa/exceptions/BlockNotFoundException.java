package com.peltikhin.atmos.jpa.exceptions;

//TODO write exception handler in controllers
public class BlockNotFoundException extends RuntimeException {
    public BlockNotFoundException(Long id) {
        super(String.format("Block with id=%d not found", id));
    }
}
