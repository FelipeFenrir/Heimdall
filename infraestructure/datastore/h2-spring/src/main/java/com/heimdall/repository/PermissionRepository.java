/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.repository;

import com.heimdall.entity.PermissionDataEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *      {@link PermissionDataEntity} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface PermissionRepository extends JpaRepository<PermissionDataEntity, Long>,
        JpaSpecificationExecutor<PermissionDataEntity> {

}
