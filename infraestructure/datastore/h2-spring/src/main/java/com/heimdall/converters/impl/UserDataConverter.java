/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.RepositoryConverter;
import com.heimdall.entity.UserDataEntity;

import com.heimdall.core.domains.model.User;
import com.heimdall.core.domains.model.implementations.UserImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link User} Domain.
 * </p>
 * <p>
 *     Convert {@link User} to {@link UserDataEntity}.
 *     Convert {@link UserDataEntity} to {@link User}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class UserDataConverter implements RepositoryConverter<UserDataEntity, User> {

    @Autowired
    private RoleDataConverter roleDataConverter;

    @Override
    public UserDataEntity mapToRepository(User domainObject) {
        return UserDataEntity.builder()
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
    public User mapToEntity(UserDataEntity tableObject) {
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
