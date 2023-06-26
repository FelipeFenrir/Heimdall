/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.IRepositoryConverter;
import com.heimdall.entity.UserDataEntity;

import com.heimdall.core.domains.factory.implementations.UserFactoryImpl;
import com.heimdall.core.domains.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class UserDataConverter implements IRepositoryConverter<UserDataEntity, User> {

    private final RoleDataConverter roleDataConverter;

    @Override
    public UserDataEntity mapToRepository(User domainObject) {
        return new UserDataEntity(
            domainObject.getId(),
            domainObject.getUsername(),
            domainObject.getEmail(),
            domainObject.getPendingEmail(),
            domainObject.getPassword(),
            domainObject.getTenantid(),
            domainObject.isEnabled(),
            domainObject.getConfirmationToken(),
            domainObject.isAcceptTerm(),
            domainObject.isAccountNonLocked(),
            domainObject.isAccountNonExpired(),
            domainObject.isCredentialsNonExpired(),
            domainObject.getRole()
                .stream()
                .map(roleDataConverter::mapToRepository)
                .collect(Collectors.toList())
        );
    }

    @Override
    public User mapToDomain(UserDataEntity tableObject) {
        return new UserFactoryImpl().create(
            tableObject.getId(),
            tableObject.getUsername(),
            tableObject.getEmail(),
            tableObject.getPassword(),
            tableObject.getTenantid(),
            tableObject.getConfirmationToken(),
            tableObject.isEnabled(),
            tableObject.isAceptTerm(),
            tableObject.isAccountNonLocked(),
            tableObject.isAccountNonExpired(),
            tableObject.isCredentialsNonExpired(),
            tableObject.getRoleDataMappers()
                .stream()
                .map(roleDataConverter::mapToDomain)
                .collect(Collectors.toList())
        );
    }
}
