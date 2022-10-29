/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.exception;

import com.heimdall.entrypoint.util.ApiMessage;

/**
 * Exception throws when Resource data is not found in Source.
 * @author Felipe de Andrade Batista
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * Constructor.
     * @param message Message of Exception.
     */
    public DataNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message Message of Exception.
     * @param cause {@link Throwable} cause of Exception.
     */
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * @param message Is {@link ApiMessage} representing message of Exception.
     */
    public DataNotFoundException(ApiMessage message) {
        super(message.getTitulo().concat(" : ").concat(message.getTexto()));
    }

    /**
     * Constructor.
     * @param message Is {@link ApiMessage} representing message of Exception.
     * @param cause {@link Throwable} cause of Exception.
     */
    public DataNotFoundException(ApiMessage message, Throwable cause) {
        super(message.getTitulo().concat(" : ").concat(message.getTexto()), cause);
    }
}
