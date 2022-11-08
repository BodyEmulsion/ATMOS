package com.peltikhin.atmos.exceptions;

public class BlockNotFoundException extends NotFoundException {
    public BlockNotFoundException(Long id) {
        super(String.format("Block with id=%d not found", id));
    }
}
