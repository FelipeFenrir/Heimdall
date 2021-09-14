/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory.implementations;

import br.com.fenrir.auth.core.entity.domain.Permission;
import br.com.fenrir.auth.core.entity.domain.implementations.PermissionImpl;
import br.com.fenrir.auth.core.entity.factory.PermissionFactory;

/**
 * <p>
 *      Domain {@link Permission} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class PermissionFactoryImpl implements PermissionFactory {
    @Override
    public Permission create(String name) {
        return PermissionImpl.builder().name(name).build();
    }

    @Override
    public Permission create(long id, String name) {
        return PermissionImpl.builder().id(id).name(name).build();
    }
}
