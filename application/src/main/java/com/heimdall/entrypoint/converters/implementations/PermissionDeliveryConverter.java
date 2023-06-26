/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters.implementations;

import com.heimdall.core.domains.model.Permission;
import com.heimdall.core.domains.model.implementations.PermissionImpl;

import com.heimdall.entrypoint.boundary.dto.implementations.PermissionDto;
import com.heimdall.entrypoint.converters.IDeliveryConverter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class PermissionDeliveryConverter implements IDeliveryConverter<PermissionDto, Permission> {

    @Override
    public PermissionDto mapToBoundary(Permission domainObject) {
        return PermissionDto.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .build();
    }

    @Override
    public Permission mapToEntity(PermissionDto boundaryObject) {
        return PermissionImpl.builder()
                .id(boundaryObject.getId())
                .name(boundaryObject.getName())
                .build();
    }
}
