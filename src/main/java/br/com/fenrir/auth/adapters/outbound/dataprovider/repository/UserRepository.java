/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.repository;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.UserDataMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 *      {@link UserDataMapper} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface UserRepository extends JpaRepository<UserDataMapper, Long>, JpaSpecificationExecutor<UserDataMapper> {

    /**
     * Find a {@link UserDataMapper} by username.
     *
     * @param username the user's username
     * @return {@link UserDataMapper} which contains the user with the given username or null.
     */
    Optional<UserDataMapper> findByUsername(String username);

    /**
     * Find a {@link UserDataMapper} by email.
     *
     * @param email the user's email
     * @return {@link UserDataMapper} which contains the user with the given email or null.
     */
    Optional<UserDataMapper> findByEmail(String email);

    /**
     * Find a {@link UserDataMapper} by confirmation token.
     *
     * @param confirmationToken generated for registration confirmation
     * @return {@link UserDataMapper} associated with this confirmation token
     */
    Optional<UserDataMapper> findByConfirmationToken(String confirmationToken);

    /**
     * Find a {@link UserDataMapper} by ID.
     *
     * @param id the user's ID
     * @return {@link Optional} {@link UserDataMapper} object which contains the user or null.
     */
    @Override
    Optional<UserDataMapper> findById(@NonNull Long id);
}