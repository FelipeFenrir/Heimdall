/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory.implementations;

import com.heimdall.core.domains.model.Permission;
import com.heimdall.core.domains.model.implementations.PermissionImpl;
import com.heimdall.core.domains.factory.PermissionFactory;

import java.util.UUID;

public class PermissionFactoryImpl implements PermissionFactory {
    @Override
    public Permission create(String name) {
        return PermissionImpl.builder().name(name).build();
    }

    @Override
    public Permission create(UUID id, String name) {
        return PermissionImpl.builder().id(id).name(name).build();
    }
}
