/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.converters.implementations;

import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.domain.implementations.UserImpl;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.UserBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.converters.DeliveryConverter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * <p>
 *     Converter from {@link User} Domain.
 * </p>
 * <p>
 *     Convert {@link User} to {@link UserBoundaryMapper}.
 *     Convert {@link UserBoundaryMapper} to {@link User}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class UserDeliveryConverter implements DeliveryConverter<UserBoundaryMapper, User> {

    @Autowired
    private RoleDeliveryConverter roleDeliveryConverter;

    @Override
    public UserBoundaryMapper mapToBoundary(User domainObject) {
        return UserBoundaryMapper.builder()
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
                .roleBoundaryMapper(domainObject.getRole().stream()
                        .map(role ->
                                roleDeliveryConverter.mapToBoundary(role))
                        .collect(Collectors.toList()))
                .createdOn(domainObject.getCreatedOn())
                .updatedOn(domainObject.getUpdatedOn())
                .build();
    }

    @Override
    public User mapToEntity(UserBoundaryMapper boundaryObject) {
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
                .role(boundaryObject.getRoleBoundaryMapper().stream()
                        .map(role ->
                                roleDeliveryConverter.mapToEntity(role))
                        .collect(Collectors.toList()))
                .createdOn(boundaryObject.getCreatedOn())
                .updatedOn(boundaryObject.getUpdatedOn())
                .build();
    }
}
