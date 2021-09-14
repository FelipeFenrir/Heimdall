/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory;

import br.com.fenrir.auth.core.entity.domain.Permission;

/**
 * <p>
 *      Interface of {@link Permission} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface PermissionFactory {
    /**
     * <p>
     *      Create a {@link Permission} Domain Entity.
     * </p>
     * @param name Name of {@link Permission}.
     * @return {@link Permission} Domain Entity.
     */
    Permission create(String name);

    /**
     * <p>
     *      Create a {@link Permission} Domain Entity.
     * </p>
     * @param id ID of {@link Permission}.
     * @param name Name of {@link Permission}.
     * @return {@link Permission} Domain Entity.
     */
    Permission create(long id, String name);
}
