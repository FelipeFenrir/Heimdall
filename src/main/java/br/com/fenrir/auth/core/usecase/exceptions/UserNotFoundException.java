/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.exceptions;

import br.com.fenrir.auth.core.entity.domain.User;

/**
 * <p>
 *     {@link User} not found exception.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class UserNotFoundException extends Exception {
    /**
     * No args constructor.
     */
    public UserNotFoundException() {
    }

    /**
     * Constructor with message.
     * @param message Message from exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * All args constructor.
     * @param message Message from exception.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
