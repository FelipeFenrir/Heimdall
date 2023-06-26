/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.repository;

import com.heimdall.entity.RoleDataEntity;

import org.jetbrains.annotations.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleDataEntity, UUID>, JpaSpecificationExecutor<RoleDataEntity> {

    Optional<RoleDataEntity> findByName(String name);

    @NotNull
    @Override
    Optional<RoleDataEntity> findById(@NotNull UUID id);
}
