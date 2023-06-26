/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.IRepositoryConverter;
import com.heimdall.entity.ResetEmailTokenDataEntity;

import com.heimdall.core.domains.model.ResetEmailToken;
import com.heimdall.core.domains.model.implementations.ResetEmailTokenImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ResetEmailTokenDataConverter implements IRepositoryConverter<ResetEmailTokenDataEntity, ResetEmailToken> {

    private final UserDataConverter userDataConverter;

    @Override
    public ResetEmailTokenDataEntity mapToRepository(ResetEmailToken domainObject) {
        return new ResetEmailTokenDataEntity(
            domainObject.getId(),
            domainObject.getPendingEmail(),
            domainObject.getResetEmailToken(),
            userDataConverter.mapToRepository(domainObject.getUser()),
            domainObject.getExpiryDate()
        );
    }

    @Override
    public ResetEmailToken mapToDomain(ResetEmailTokenDataEntity tableObject) {
        return ResetEmailTokenImpl.builder()
                .id(tableObject.getId())
                .pendingEmail(tableObject.getPendingEmail())
                .resetEmailToken(tableObject.getResetEmailToken())
                .expiryDate(tableObject.getExpiryDate())
                .user(userDataConverter.mapToDomain(tableObject.getUserDataMapper()))
                .build();
    }
}
