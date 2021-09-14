/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.ports;

import br.com.fenrir.auth.core.entity.domain.ResetEmailToken;

import java.util.Optional;

/**
 * <p>
 *      {@link ResetEmailToken} gateway operations.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ResetEmailTokenGateway {
    /**
     * <p>
     *     Save {@link ResetEmailToken}.
     * </p>
     * @param resetEmailToken {@link ResetEmailToken} domain entity.
     * @return {@link ResetEmailToken}.
     */
    ResetEmailToken save(ResetEmailToken resetEmailToken);

    /**
     * <p>
     *     Delete {@link ResetEmailToken}.
     * </p>
     * @param resetEmailToken {@link ResetEmailToken} domain entity.
     */
    void delete(ResetEmailToken resetEmailToken);

    /**
     * Find a reset email token.
     *
     * @param resetEmailToken the token for Reset email.
     * @return {@link ResetEmailToken} in {@link Optional}.
     */
    Optional<ResetEmailToken> findByToken(String resetEmailToken);
}
