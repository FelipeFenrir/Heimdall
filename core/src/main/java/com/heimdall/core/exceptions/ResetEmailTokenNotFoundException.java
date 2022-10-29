/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.exceptions;

import com.heimdall.core.domains.model.ResetEmailToken;

/**
 * <p>
 *     {@link ResetEmailToken} not found exception.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class ResetEmailTokenNotFoundException extends RuntimeException {
    /**
     * No args constructor.
     */
    public ResetEmailTokenNotFoundException() {
    }

    /**
     * Constructor with message.
     * @param message Message from exception.
     */
    public ResetEmailTokenNotFoundException(String message) {
        super(message);
    }

    /**
     * All args constructor.
     * @param message Message from exception.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public ResetEmailTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public ResetEmailTokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
