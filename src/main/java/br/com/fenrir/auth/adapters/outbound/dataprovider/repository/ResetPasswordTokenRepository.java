/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.repository;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.ResetPasswordTokenDataMapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 *      {@link ResetPasswordTokenDataMapper} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordTokenDataMapper, Long>,
        JpaSpecificationExecutor<ResetPasswordTokenDataMapper> {

    /**
     * <p>
     *      Find a Reset Password Token.
     * </p>
     * @param resetPassToken the token for Reset password.
     * @return {@link ResetPasswordTokenDataMapper} which contains the token with the given data
     * or null.
     */
    Optional<ResetPasswordTokenDataMapper> findByResetPassToken(String resetPassToken);
}
