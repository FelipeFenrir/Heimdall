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

/**
 * <p>
 *      {@link UserDataEntity} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface UserRepository extends JpaRepository<UserDataEntity, Long>, JpaSpecificationExecutor<UserDataEntity> {

    /**
     * Find a {@link UserDataEntity} by username.
     *
     * @param username the user's username
     * @return {@link UserDataEntity} which contains the user with the given username or null.
     */
    Optional<UserDataEntity> findByUsername(String username);

    /**
     * Find a {@link UserDataEntity} by email.
     *
     * @param email the user's email
     * @return {@link UserDataEntity} which contains the user with the given email or null.
     */
    Optional<UserDataEntity> findByEmail(String email);

    /**
     * Find a {@link UserDataEntity} by confirmation token.
     *
     * @param confirmationToken generated for registration confirmation
     * @return {@link UserDataEntity} associated with this confirmation token
     */
    Optional<UserDataEntity> findByConfirmationToken(String confirmationToken);

    /**
     * Find a {@link UserDataEntity} by ID.
     *
     * @param id the user's ID
     * @return {@link Optional} {@link UserDataEntity} object which contains the user or null.
     */
    @NotNull
    @Override
    Optional<UserDataEntity> findById(@NonNull Long id);
}
