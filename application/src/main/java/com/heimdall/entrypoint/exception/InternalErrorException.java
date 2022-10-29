/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.exception;

import com.heimdall.entrypoint.util.ApiMessage;

/**
 * <p>Exception throws when a internal error occurs.</p>
 * <p>
 *     This error may be an exception not handled by logic.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public class InternalErrorException extends RuntimeException {

    /**
     * Constructor.
     * @param message Message of Exception.
     */
    public InternalErrorException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message Message of Exception.
     * @param cause {@link Throwable} cause of Exception.
     */
    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * @param message Is {@link ApiMessage} representing message of Exception.
     */
    public InternalErrorException(ApiMessage message) {
        super(message.getTitulo().concat(" : ").concat(message.getTexto()));
    }

    /**
     * Constructor.
     * @param message Is {@link ApiMessage} representing message of Exception.
     * @param cause {@link Throwable} cause of Exception.
     */
    public InternalErrorException(ApiMessage message, Throwable cause) {
        super(message.getTitulo().concat(" : ").concat(message.getTexto()), cause);
    }
}
