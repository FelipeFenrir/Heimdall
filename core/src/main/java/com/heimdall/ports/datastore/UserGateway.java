/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.ports.datastore;

import com.heimdall.core.domains.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * <p>
 *      {@link User} gateway operations.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface UserGateway {

    /**
     * <p>
     *     Save {@link User}.
     * </p>
     * @param user {@link User} domain entity.
     * @return {@link User}.
     */
    User save(User user);

    /**
     * <p>
     *     Delete {@link User}.
     * </p>
     * @param user {@link User} domain entity.
     */
    void delete(User user);

    /**
     * <p>
     *     Find {@link User} by ID.
     * </p>
     * @param id {@link User} ID.
     * @return {@link User} in {@link Optional}.
     */
    Optional<User> findById(UUID id);

    /**
     * <p>
     *     Find {@link User} by email.
     * </p>
     * @param email email of {@link User}.
     * @return {@link User} in {@link Optional}.
     */
    Optional<User> findByEmail(String email);

    /**
     * <p>
     *     Find {@link User} by username.
     * </p>
     * @param username username of {@link User}.
     * @return {@link User} in {@link Optional}.
     */
    Optional<User> findByUsername(String username);

    /**
     * <p>
     *     Find {@link User} by confirmation token.
     * </p>
     * @param token confirmation token from {@link User}.
     * @return {@link User} in {@link Optional}.
     */
    Optional<User> findByConfirmationToken(String token);
}
