/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.ports.datastore;

import com.heimdall.core.domains.model.Permission;

import java.util.Optional;
import java.util.UUID;

/**
 * <p>
 *     {@link Permission} gateway interface.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface PermissionGateway {

    /**
     * <p>
     *      Find a {@link Permission} by name.
     * </p>
     * @param permission {@link Permission} name.
     * @return {@link Permission} returns an {@link Optional} with
     * {@link Permission} object which contains the role or null.
     */
    Optional<Permission> findByName(String permission);

    /**
     * <p>
     *      Find a {@link Permission} by ID.
     * </p>
     * @param id {@link Permission} ID.
     * @return {@link Permission} returns an {@link Optional} with
     * {@link Permission} object which contains the role or null.
     */
    Optional<Permission> findById(UUID id);
}
