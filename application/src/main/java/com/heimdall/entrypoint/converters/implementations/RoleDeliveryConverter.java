/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters.implementations;

import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.implementations.RoleImpl;

import com.heimdall.entrypoint.boundary.dto.implementations.RoleDto;
import com.heimdall.entrypoint.converters.IDeliveryConverter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class RoleDeliveryConverter implements IDeliveryConverter<RoleDto, Role> {

    private final PermissionDeliveryConverter permissionDeliveryConverter;

    @Override
    public RoleDto mapToBoundary(Role domainObject) {
        return RoleDto.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .permissions(domainObject.getPermission().stream()
                        .map(permissionDeliveryConverter::mapToBoundary)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Role mapToEntity(RoleDto boundaryObject) {
        return RoleImpl.builder()
                .id(boundaryObject.getId())
                .name(boundaryObject.getName())
                .permission(
                        boundaryObject.getPermissions().stream()
                            .map(permissionDeliveryConverter::mapToEntity)
                            .collect(Collectors.toList())
                )
                .build();
    }
}
