package org.springclass.springclassproject.exception;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException {
    @Getter
    private final String error;
    private final String message;

    public ResourceNotFoundException(final String error, final String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
