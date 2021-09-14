/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.exceptions;

import br.com.fenrir.auth.core.entity.domain.User;

/**
 * <p>
 *     {@link User} not enabled exception.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class UserNotEnabledException extends Exception {

    /**
     * No args constructor.
     */
    public UserNotEnabledException() {
    }

    /**
     * Constructor with message.
     * @param message Message from exception.
     */
    public UserNotEnabledException(String message) {
        super(message);
    }

    /**
     * All args constructor.
     * @param message Message from exception.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public UserNotEnabledException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     * @param cause {@link Throwable} by other Exception cause.
     */
    public UserNotEnabledException(Throwable cause) {
        super(cause);
    }
}
