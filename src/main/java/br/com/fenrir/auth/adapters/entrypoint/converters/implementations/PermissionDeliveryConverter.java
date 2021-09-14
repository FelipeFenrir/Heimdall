/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.converters.implementations;

import br.com.fenrir.auth.core.entity.domain.Permission;
import br.com.fenrir.auth.core.entity.domain.implementations.PermissionImpl;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.PermissionBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.converters.DeliveryConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     Converter from {@link Permission} Domain.
 * </p>
 * <p>
 *     Convert {@link Permission} to {@link PermissionBoundaryMapper}.
 *     Convert {@link PermissionBoundaryMapper} to {@link Permission}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class PermissionDeliveryConverter implements DeliveryConverter<PermissionBoundaryMapper, Permission> {

    @Override
    public PermissionBoundaryMapper mapToBoundary(Permission domainObject) {
        return PermissionBoundaryMapper.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .build();
    }

    @Override
    public Permission mapToEntity(PermissionBoundaryMapper boundaryObject) {
        return PermissionImpl.builder()
                .id(boundaryObject.getId())
                .name(boundaryObject.getName())
                .build();
    }
}
