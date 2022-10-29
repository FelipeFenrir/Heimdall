/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.ports.command;

import com.heimdall.core.domains.model.User;
import com.heimdall.core.exceptions.UserNotFoundException;

/**
 * <p>
 *      {@link User} actions use case interface.
 * </p>
 * <p>
 *     This class use Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface UserCommand {

    //TODO: Extends of Command Port

    /**
     * <p>
     *     Get {@link User} data.
     * </p>
     * @param username {@link User} username.
     * @return {@link User}.
     */
    User getUser(String username) throws UserNotFoundException;

    /**
     * <p>
     *     Delete {@link User}.
     * </p>
     * @param username {@link User} username.
     */
    void deleteUser(String username) throws UserNotFoundException;

    /**
     * <p>
     *     Revoke all tokens to logout {@link User}.
     * </p>
     * @param username {@link User} usename.
     */
    void revokeAllTokens(String username);
}
