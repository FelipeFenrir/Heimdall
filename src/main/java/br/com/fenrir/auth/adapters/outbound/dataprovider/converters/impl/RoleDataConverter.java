/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.RoleDataMapper;
import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.RepositoryConverter;
import br.com.fenrir.auth.core.entity.domain.Role;
import br.com.fenrir.auth.core.entity.domain.implementations.RoleImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link Role} Domain.
 * </p>
 * <p>
 *     Convert {@link Role} to {@link RoleDataMapper}.
 *     Convert {@link RoleDataMapper} to {@link Role}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class RoleDataConverter implements RepositoryConverter<RoleDataMapper, Role> {

    @Autowired
    private PermissionDataConverter permissionDataConverter;

    @Override
    public RoleDataMapper mapToRepository(Role domainObject) {
        return RoleDataMapper.builder()
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
    public RoleImpl mapToEntity(RoleDataMapper tableObject) {
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
