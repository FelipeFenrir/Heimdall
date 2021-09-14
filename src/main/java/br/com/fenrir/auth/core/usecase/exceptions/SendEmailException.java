/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.exceptions;

import br.com.fenrir.auth.core.entity.domain.Email;

/**
 * <p>
 *     Exception to send {@link Email}.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class SendEmailException extends Exception {
    /**
     * No args constructor.
     */
    public SendEmailException() {
    }

    /**
     * Constructor with message.
     * @param message Message from exception.
     */
    public SendEmailException(String message) {
        super(message);
    }

    /**
     * All args constructor.
     * @param message Message from exception.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public SendEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public SendEmailException(Throwable cause) {
        super(cause);
    }
}