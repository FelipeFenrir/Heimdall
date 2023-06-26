/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.core.domains.factory.implementations.RoleFactoryImpl;
import com.heimdall.core.domains.model.Role;

import com.heimdall.converters.IRepositoryConverter;
import com.heimdall.entity.RoleDataEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class RoleDataConverter implements IRepositoryConverter<RoleDataEntity, Role> {

    private final PermissionDataConverter permissionDataConverter;

    @Override
    public RoleDataEntity mapToRepository(Role domainObject) {
        return new RoleDataEntity(
            domainObject.getId(),
            domainObject.getName(),
            domainObject.getPermission()
                .stream()
                .map(permissionDataConverter::mapToRepository)
                .collect(Collectors.toList())
        );
    }

    @Override
    public Role mapToDomain(RoleDataEntity tableObject) {
        return new RoleFactoryImpl().create(
            tableObject.getId(),
            tableObject.getName(),
            tableObject.getPermissionDataMappers()
                .stream()
                .map(permissionDataConverter::mapToDomain)
                .collect(Collectors.toList())
        );
    }
}
