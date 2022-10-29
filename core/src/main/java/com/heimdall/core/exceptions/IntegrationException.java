package com.heimdall.core.exceptions;

import com.heimdall.core.exceptions.util.ErrorMessage;

import java.util.List;

public class IntegrationException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public IntegrationException(String code, String message) {
        super();
        this.errorMessage = new ErrorMessage(code, message, List.of());
    }

    public IntegrationException(String code, String message, String reasonMessage) {
        super(message);
        this.errorMessage = new ErrorMessage(code, reasonMessage, List.of());
    }

    public ErrorMessage getError() {
        return this.errorMessage;
    }
}
