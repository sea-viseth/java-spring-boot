package org.springclass.springclassproject.exception;

import lombok.Getter;

//for error validation inside our function
public class PlateformApiDataValidationException extends RuntimeException {
    @Getter
    private final String error;
    private final String message;

    public PlateformApiDataValidationException(final String error, final String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

