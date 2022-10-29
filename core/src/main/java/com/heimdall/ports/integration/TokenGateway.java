/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.ports.integration;

/**
 * <p>
 *      Token Gateway interface.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface TokenGateway {

    /**
     * <p>
     *      Revoke access and refresh tokens for user with given username.
     * </p>
     *
     * @param username String
     */
    void revokeTokens(String username);
}
