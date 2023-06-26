/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.repository;

import com.heimdall.entity.ResetEmailTokenDataEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResetEmailTokenRepository extends JpaRepository<ResetEmailTokenDataEntity, UUID>,
        JpaSpecificationExecutor<ResetEmailTokenDataEntity> {

    Optional<ResetEmailTokenDataEntity> findByResetEmailToken(String resetEmailToken);
}
