/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.RepositoryConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.UserDataMapper;
import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.domain.implementations.UserImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link User} Domain.
 * </p>
 * <p>
 *     Convert {@link User} to {@link UserDataMapper}.
 *     Convert {@link UserDataMapper} to {@link User}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class UserDataConverter implements RepositoryConverter<UserDataMapper, User> {

    @Autowired
    private RoleDataConverter roleDataConverter;

    @Override
    public UserDataMapper mapToRepository(User domainObject) {
        return UserDataMapper.builder()
                .id(domainObject.getId())
                .username(domainObject.getUsername())
                .email(domainObject.getEmail())
                .password(domainObject.getPassword())
                .tenantid(domainObject.getTenantid())
                .confirmationToken(domainObject.getConfirmationToken())
                .enabled(domainObject.isEnabled())
                .aceptTerm(domainObject.isAcceptTerm())
                .accountNonExpired(domainObject.isAccountNonExpired())
                .accountNonLocked(domainObject.isAccountNonLocked())
                .credentialsNonExpired(domainObject.isCredentialsNonExpired())
                .roleDataMappers(
                        domainObject.getRole().stream().map(role ->
                                roleDataConverter.mapToRepository(role))
                            .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public User mapToEntity(UserDataMapper tableObject) {
        return UserImpl.builder()
                .id(tableObject.getId())
                .username(tableObject.getUsername())
                .email(tableObject.getEmail())
                .password(tableObject.getPassword())
                .tenantid(tableObject.getTenantid())
                .confirmationToken(tableObject.getConfirmationToken())
                .enabled(tableObject.isEnabled())
                .acceptTerm(tableObject.isAceptTerm())
                .accountNonExpired(tableObject.isAccountNonExpired())
                .accountNonLocked(tableObject.isAccountNonLocked())
                .credentialsNonExpired(tableObject.isCredentialsNonExpired())
                .role(
                        tableObject.getRoleDataMappers().stream().map(roleDataMapper ->
                                roleDataConverter.mapToEntity(roleDataMapper))
                                .collect(Collectors.toList()))
                .createdOn(tableObject.getCreatedOn())
                .updatedOn(tableObject.getUpdatedOn())
                .build();
    }
}
