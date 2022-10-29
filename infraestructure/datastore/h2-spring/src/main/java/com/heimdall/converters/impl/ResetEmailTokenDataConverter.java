/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.RepositoryConverter;
import com.heimdall.entity.ResetEmailTokenDataEntity;

import com.heimdall.core.domains.model.ResetEmailToken;
import com.heimdall.core.domains.model.implementations.ResetEmailTokenImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *     Converter from {@link ResetEmailToken} Domain.
 * </p>
 * <p>
 *     Convert {@link ResetEmailToken} to {@link ResetEmailTokenDataEntity}.
 *     Convert {@link ResetEmailTokenDataEntity} to {@link ResetEmailToken}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class ResetEmailTokenDataConverter implements RepositoryConverter<ResetEmailTokenDataEntity, ResetEmailToken> {

    @Autowired
    private UserDataConverter userDataConverter;

    @Override
    public ResetEmailTokenDataEntity mapToRepository(ResetEmailToken domainObject) {
        return ResetEmailTokenDataEntity.builder()
                .id(domainObject.getId())
                .pendingEmail(domainObject.getPendingEmail())
                .resetEmailToken(domainObject.getResetEmailToken())
                .expiryDate(domainObject.getExpiryDate())
                .userDataMapper(userDataConverter.mapToRepository(domainObject.getUser()))
                .build();
    }

    @Override
    public ResetEmailToken mapToEntity(ResetEmailTokenDataEntity tableObject) {
        return ResetEmailTokenImpl.builder()
                .id(tableObject.getId())
                .pendingEmail(tableObject.getPendingEmail())
                .resetEmailToken(tableObject.getResetEmailToken())
                .expiryDate(tableObject.getExpiryDate())
                .user(userDataConverter.mapToEntity(tableObject.getUserDataMapper()))
                .build();
    }
}
