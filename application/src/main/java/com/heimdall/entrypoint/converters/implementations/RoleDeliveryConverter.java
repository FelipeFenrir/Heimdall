/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters.implementations;

import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.implementations.RoleImpl;

import com.heimdall.entrypoint.boundary.dto.implementations.RoleDto;
import com.heimdall.entrypoint.converters.IDeliveryConverter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link Role} Domain.
 * </p>
 * <p>
 *     Convert {@link Role} to {@link RoleDto}.
 *     Convert {@link RoleDto} to {@link Role}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class RoleDeliveryConverter implements IDeliveryConverter<RoleDto, Role> {

    @Autowired
    private PermissionDeliveryConverter permissionDeliveryConverter;

    @Override
    public RoleDto mapToBoundary(Role domainObject) {
        return RoleDto.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .permissionBoundaryMappers(domainObject.getPermission().stream()
                        .map(permission ->
                                permissionDeliveryConverter.mapToBoundary(permission))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Role mapToEntity(RoleDto boundaryObject) {
        return RoleImpl.builder()
                .id(boundaryObject.getId())
                .name(boundaryObject.getName())
                .permission(
                        boundaryObject.getPermissionBoundaryMappers().stream()
                            .map(permissionBoundary ->
                                    permissionDeliveryConverter.mapToEntity(permissionBoundary))
                            .collect(Collectors.toList())
                )
                .build();
    }
}
