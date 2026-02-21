package com.register.backend.rest.error;

public class UnknownEntityException extends RuntimeException {

    private final String errorCode;

    public UnknownEntityException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
