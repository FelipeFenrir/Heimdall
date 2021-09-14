/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory;

import br.com.fenrir.auth.core.entity.domain.Permission;
import br.com.fenrir.auth.core.entity.domain.Role;

import java.util.Collection;

/**
 * <p>
 *      Interface of {@link Role} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface RoleFactory {
    /**
     * <p>
     *      Create a {@link Role} Domain Entity.
     * </p>
     * @param name Name of {@link Role}.
     * @return {@link Role} Domain Entity.
     */
    Role create(String name);

    /**
     * <p>
     *      Create a {@link Role} Domain Entity.
     * </p>
     * @param id ID of {@link Role}.
     * @param name Name of {@link Role}.
     * @param permissions List of {@link Permission} from {@link Role}.
     * @return {@link Role} Domain Entity.
     */
    Role create(long id, String name, Collection<Permission> permissions);

}
