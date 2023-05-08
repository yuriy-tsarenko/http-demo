package com.goit.exception;

import lombok.Getter;

public class ServerInternalErrorException extends RuntimeException {
    @Getter
    private final int httpCode = 500;

    public ServerInternalErrorException(String message) {
        super(message);
    }

    public ServerInternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
