/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.core.domains.factory.implementations.PermissionFactoryImpl;
import com.heimdall.core.domains.model.Permission;

import com.heimdall.converters.IRepositoryConverter;
import com.heimdall.entity.PermissionDataEntity;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class PermissionDataConverter implements IRepositoryConverter<PermissionDataEntity, Permission> {

    @Override
    public PermissionDataEntity mapToRepository(Permission domainObject) {
        return new PermissionDataEntity(
            domainObject.getId(),
            domainObject.getName()
        );
    }

    @Override
    public Permission mapToDomain(PermissionDataEntity tableObject) {
        return new PermissionFactoryImpl().create(
                tableObject.getId(),
                tableObject.getName()
        );
    }
}
