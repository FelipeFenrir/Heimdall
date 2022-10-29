/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.RepositoryConverter;
import com.heimdall.entity.PermissionDataEntity;

import com.heimdall.core.domains.model.Permission;
import com.heimdall.core.domains.model.implementations.PermissionImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     Converter from {@link Permission} Domain.
 * </p>
 * <p>
 *     Convert {@link Permission} to {@link PermissionDataEntity}.
 *     Convert {@link PermissionDataEntity} to {@link Permission}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class PermissionDataConverter implements RepositoryConverter<PermissionDataEntity, Permission> {

    @Override
    public PermissionDataEntity mapToRepository(Permission domainObject) {
        return PermissionDataEntity.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .build();
    }

    @Override
    public Permission mapToEntity(PermissionDataEntity tableObject) {
        return PermissionImpl.builder()
                .id(tableObject.getId())
                .name(tableObject.getName())
                .build();
    }
}
