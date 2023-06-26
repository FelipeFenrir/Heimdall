/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.repository;

import com.heimdall.entity.UserDataEntity;

import org.jetbrains.annotations.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDataEntity, UUID>, JpaSpecificationExecutor<UserDataEntity> {

    Optional<UserDataEntity> findByUsername(String username);

    Optional<UserDataEntity> findByEmail(String email);

    Optional<UserDataEntity> findByConfirmationToken(String confirmationToken);

    @NotNull
    @Override
    Optional<UserDataEntity> findById(@NonNull UUID id);
}
