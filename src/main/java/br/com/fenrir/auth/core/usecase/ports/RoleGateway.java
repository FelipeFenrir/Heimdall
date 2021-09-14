/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.ports;

import br.com.fenrir.auth.core.entity.domain.Role;

import java.util.Optional;

/**
 * <p>
 *     {@link Role} gateway interface.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface RoleGateway {

    /**
     * <p>
     *      Find a {@link Role} by name.
     * </p>
     * @param rolename {@link Role} name.
     * @return {@link Role} returns an {@link Optional} with {@link Role} object which contains the role or null.
     */
    Optional<Role> findByName(String rolename);

    /**
     * <p>
     *      Find a {@link Role} by ID.
     * </p>
     * @param id {@link Role} ID.
     * @return {@link Role} returns an {@link Optional} with {@link Role} object which contains the role or null.
     */
    Optional<Role> findById(Long id);
}
