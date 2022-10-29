/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.repository;

import com.heimdall.entity.ResetPasswordTokenDataEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 *      {@link ResetPasswordTokenDataEntity} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordTokenDataEntity, Long>,
        JpaSpecificationExecutor<ResetPasswordTokenDataEntity> {

    /**
     * <p>
     *      Find a Reset Password Token.
     * </p>
     * @param resetPassToken the token for Reset password.
     * @return {@link ResetPasswordTokenDataEntity} which contains the token with the given data
     * or null.
     */
    Optional<ResetPasswordTokenDataEntity> findByResetPassToken(String resetPassToken);
}
