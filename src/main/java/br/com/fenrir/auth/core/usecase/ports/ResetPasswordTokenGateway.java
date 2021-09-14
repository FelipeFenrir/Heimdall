/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.ports;

import br.com.fenrir.auth.core.entity.domain.ResetPasswordToken;

import java.util.Optional;

/**
 * <p>
 *      {@link ResetPasswordToken} gateway operations.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ResetPasswordTokenGateway {

    /**
     * <p>
     *     Save {@link ResetPasswordToken}.
     * </p>
     * @param resetPasswordToken {@link ResetPasswordToken} domain entity.
     * @return {@link ResetPasswordToken}.
     */
    ResetPasswordToken save(ResetPasswordToken resetPasswordToken);

    /**
     * <p>
     *     Delete {@link ResetPasswordToken}.
     * </p>
     * @param resetPasswordToken {@link ResetPasswordToken} domain entity.
     */
    void delete(ResetPasswordToken resetPasswordToken);

    /**
     * Find a reset password token.
     *
     * @param resetPassToken the token for Reset password.
     * @return {@link ResetPasswordToken} in {@link Optional}.
     */
    Optional<ResetPasswordToken> findByToken(String resetPassToken);
}
