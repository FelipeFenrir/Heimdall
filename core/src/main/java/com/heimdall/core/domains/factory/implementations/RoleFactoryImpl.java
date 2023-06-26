/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory.implementations;

import com.heimdall.core.domains.model.Permission;
import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.implementations.RoleImpl;
import com.heimdall.core.domains.factory.RoleFactory;

import java.util.Collection;
import java.util.UUID;

public class RoleFactoryImpl implements RoleFactory {

    @Override
    public Role create(String name) {
        return RoleImpl.builder().name(name).build();
    }

    @Override
    public Role create(UUID id, String name, Collection<Permission> permissions) {
        return RoleImpl.builder().id(id).name(name).permission(permissions).build();
    }
}
