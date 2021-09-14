/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory.implementations;

import br.com.fenrir.auth.core.entity.domain.Permission;
import br.com.fenrir.auth.core.entity.domain.Role;
import br.com.fenrir.auth.core.entity.domain.implementations.RoleImpl;
import br.com.fenrir.auth.core.entity.factory.RoleFactory;

import java.util.Collection;

/**
 * <p>
 *      Domain {@link Role} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class RoleFactoryImpl implements RoleFactory {
    @Override
    public Role create(String name) {
        return RoleImpl.builder().name(name).build();
    }

    @Override
    public Role create(long id, String name, Collection<Permission> permissions) {
        return RoleImpl.builder().id(id).name(name).permission(permissions).build();
    }
}
