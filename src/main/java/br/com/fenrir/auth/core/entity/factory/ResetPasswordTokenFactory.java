/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory;

import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.domain.ResetPasswordToken;

import java.time.LocalDateTime;

/**
 * <p>
 *      Interface of {@link ResetPasswordToken} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ResetPasswordTokenFactory {

    /**
     * <p>
     *      Create a New {@link ResetPasswordToken} Domain Entity.
     * </p>
     * @param minutesToExpiry Expiry Date of Token.
     * @param user User of {@link ResetPasswordToken}.
     * @return {@link ResetPasswordToken} Domain Entity.
     */
    ResetPasswordToken create(long minutesToExpiry, User user);

    /**
     * <p>
     *      Create a {@link ResetPasswordToken} Domain Entity.
     * </p>
     * @param id of {@link ResetPasswordToken}.
     * @param token Token.
     * @param expiryDate Expiry Date of Token.
     * @param user User of {@link ResetPasswordToken}.
     * @return {@link ResetPasswordToken} Domain Entity.
     */
    ResetPasswordToken create(Long id, String token, LocalDateTime expiryDate, User user);
}
