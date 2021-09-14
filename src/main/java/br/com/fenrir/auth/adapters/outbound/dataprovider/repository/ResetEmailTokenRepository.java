/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.repository;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.ResetEmailTokenDataMapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 *      {@link ResetEmailTokenDataMapper} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface ResetEmailTokenRepository extends JpaRepository<ResetEmailTokenDataMapper, Long>,
        JpaSpecificationExecutor<ResetEmailTokenDataMapper> {

    /**
     * <p>
     *      Find a Reset Email Token.
     * </p>
     * @param resetEmailToken the token for Reset email.
     * @return {@link ResetEmailTokenDataMapper} which contains the token with the given data
     * or null.
     */
    Optional<ResetEmailTokenDataMapper> findByResetEmailToken(String resetEmailToken);
}
