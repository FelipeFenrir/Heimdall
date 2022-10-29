/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory;

import com.heimdall.core.domains.model.ResetEmailToken;
import com.heimdall.core.domains.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *      Interface of {@link ResetEmailToken} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ResetEmailTokenFactory {
    /**
     * <p>
     *      Create a New {@link ResetEmailToken} Domain Entity.
     * </p>
     * @param pendingEmail The Pending Email of User (New Email).
     * @param minutesToExpiry Expiry Date of Token.
     * @param user User of {@link ResetEmailToken}.
     * @return {@link ResetEmailToken} Domain Entity.
     */
    ResetEmailToken create(String pendingEmail, long minutesToExpiry, User user);

    /**
     * <p>
     *      Create a {@link ResetEmailToken} Domain Entity.
     * </p>
     * @param id of {@link ResetEmailToken}.
     * @param pendingEmail The Pending Email of User (New Email).
     * @param token Token.
     * @param expiryDate Expiry Date of Token.
     * @param user User of {@link ResetEmailToken}.
     * @return {@link ResetEmailToken} Domain Entity.
     */
    ResetEmailToken create(UUID id, String pendingEmail, String token, LocalDateTime expiryDate, User user);
}
