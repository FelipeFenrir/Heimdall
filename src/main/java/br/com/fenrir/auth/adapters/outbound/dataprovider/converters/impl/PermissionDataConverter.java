/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.RepositoryConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.PermissionDataMapper;
import br.com.fenrir.auth.core.entity.domain.Permission;
import br.com.fenrir.auth.core.entity.domain.implementations.PermissionImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     Converter from {@link Permission} Domain.
 * </p>
 * <p>
 *     Convert {@link Permission} to {@link PermissionDataMapper}.
 *     Convert {@link PermissionDataMapper} to {@link Permission}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class PermissionDataConverter implements RepositoryConverter<PermissionDataMapper, Permission> {

    @Override
    public PermissionDataMapper mapToRepository(Permission domainObject) {
        return PermissionDataMapper.builder()
                .id(domainObject.getId())
                .name(domainObject.getName())
                .build();
    }

    @Override
    public Permission mapToEntity(PermissionDataMapper tableObject) {
        return PermissionImpl.builder()
                .id(tableObject.getId())
                .name(tableObject.getName())
                .build();
    }
}
