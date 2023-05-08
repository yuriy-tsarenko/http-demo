package com.goit.exception;

import lombok.Getter;

public class ServerNotFoundException extends ServerInternalErrorException {
    @Getter
    private final int httpCode = 400;

    public ServerNotFoundException(String message) {
        super(message);
    }

    public ServerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
