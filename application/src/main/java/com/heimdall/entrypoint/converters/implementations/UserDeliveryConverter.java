/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters.implementations;

import com.heimdall.core.domains.model.User;
import com.heimdall.core.domains.model.implementations.UserImpl;

import com.heimdall.entrypoint.boundary.dto.implementations.UserDto;
import com.heimdall.entrypoint.converters.IDeliveryConverter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class UserDeliveryConverter implements IDeliveryConverter<UserDto, User> {

    private final RoleDeliveryConverter roleDeliveryConverter;

    @Override
    public UserDto mapToBoundary(User domainObject) {
        return UserDto.builder()
                .id(domainObject.getId())
                .username(domainObject.getUsername())
                .password(domainObject.getPassword())
                .email(domainObject.getEmail())
                .tenantid(domainObject.getTenantid())
                .enabled(domainObject.isEnabled())
                .acceptTerm(domainObject.isAcceptTerm())
                .confirmationToken(domainObject.getConfirmationToken())
                .accountNonExpired(domainObject.isAccountNonExpired())
                .accountNonLocked(domainObject.isAccountNonLocked())
                .credentialsNonExpired(domainObject.isCredentialsNonExpired())
                .roles(domainObject.getRole().stream()
                        .map(role ->
                                roleDeliveryConverter.mapToBoundary(role))
                        .collect(Collectors.toList()))
                .createdOn(domainObject.getCreatedOn())
                .updatedOn(domainObject.getUpdatedOn())
                .build();
    }

    @Override
    public User mapToEntity(UserDto boundaryObject) {
        return UserImpl.builder()
                .id(boundaryObject.getId())
                .username(boundaryObject.getUsername())
                .password(boundaryObject.getPassword())
                .email(boundaryObject.getEmail())
                .tenantid(boundaryObject.getTenantid())
                .enabled(boundaryObject.isEnabled())
                .acceptTerm(boundaryObject.isAcceptTerm())
                .confirmationToken(boundaryObject.getConfirmationToken())
                .accountNonExpired(boundaryObject.isAccountNonExpired())
                .accountNonLocked(boundaryObject.isAccountNonLocked())
                .credentialsNonExpired(boundaryObject.isCredentialsNonExpired())
                .role(boundaryObject.getRoles().stream()
                        .map(role ->
                                roleDeliveryConverter.mapToEntity(role))
                        .collect(Collectors.toList()))
                .createdOn(boundaryObject.getCreatedOn())
                .updatedOn(boundaryObject.getUpdatedOn())
                .build();
    }
}
