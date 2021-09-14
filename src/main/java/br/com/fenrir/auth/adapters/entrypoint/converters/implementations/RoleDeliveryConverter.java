/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.converters.implementations;

import br.com.fenrir.auth.core.entity.domain.Role;
import br.com.fenrir.auth.core.entity.domain.implementations.RoleImpl;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.RoleBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.converters.DeliveryConverter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link Role} Domain.
 * </p>
 * <p>
 *     Convert {@link Role} to {@link RoleBoundaryMapper}.
 *     Convert {@link RoleBoundaryMapper} to {@link Role}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class RoleDeliveryConverter implements DeliveryConverter<RoleBoundaryMapper, Role> {

    @Autowired
    private PermissionDeliveryConverter permissionDeliveryConverter;

    @Override
    public RoleBoundaryMapper mapToBoundary(Role domainObject) {
        return RoleBoundaryMapper.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .permissionBoundaryMappers(domainObject.getPermission().stream()
                        .map(permission ->
                                permissionDeliveryConverter.mapToBoundary(permission))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Role mapToEntity(RoleBoundaryMapper boundaryObject) {
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
