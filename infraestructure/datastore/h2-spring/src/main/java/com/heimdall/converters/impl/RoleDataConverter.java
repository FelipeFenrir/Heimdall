/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.RepositoryConverter;
import com.heimdall.entity.RoleDataEntity;

import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.implementations.RoleImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link Role} Domain.
 * </p>
 * <p>
 *     Convert {@link Role} to {@link RoleDataEntity}.
 *     Convert {@link RoleDataEntity} to {@link Role}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class RoleDataConverter implements RepositoryConverter<RoleDataEntity, Role> {

    @Autowired
    private PermissionDataConverter permissionDataConverter;

    @Override
    public RoleDataEntity mapToRepository(Role domainObject) {
        return RoleDataEntity.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .permissionDataMappers(
                        domainObject.getPermission().stream()
                                .map(permission ->
                                        permissionDataConverter.mapToRepository(permission))
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public RoleImpl mapToEntity(RoleDataEntity tableObject) {
        return RoleImpl.builder()
                .id(tableObject.getId())
                .name(tableObject.getName())
                .permission(
                        tableObject.getPermissionDataMappers().stream()
                                .map(permissionDataMapper ->
                                        permissionDataConverter.mapToEntity(permissionDataMapper))
                                .collect(Collectors.toList()))
                .build();
    }
}
