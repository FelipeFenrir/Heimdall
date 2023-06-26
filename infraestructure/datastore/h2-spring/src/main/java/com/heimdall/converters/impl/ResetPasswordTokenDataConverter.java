/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.IRepositoryConverter;
import com.heimdall.entity.ResetPasswordTokenDataEntity;

import com.heimdall.core.domains.factory.implementations.ResetPasswordTokenFactoryImpl;
import com.heimdall.core.domains.model.ResetPasswordToken;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ResetPasswordTokenDataConverter implements IRepositoryConverter<ResetPasswordTokenDataEntity,
        ResetPasswordToken> {

    private final UserDataConverter userDataConverter;

    @Override
    public ResetPasswordTokenDataEntity mapToRepository(ResetPasswordToken domainObject) {
        return new ResetPasswordTokenDataEntity(
            domainObject.getId(),
            domainObject.getResetPassToken(),
            userDataConverter.mapToRepository(domainObject.getUser()),
            domainObject.getExpiryDate()
        );
    }

    @Override
    public ResetPasswordToken mapToDomain(ResetPasswordTokenDataEntity tableObject) {
        return new ResetPasswordTokenFactoryImpl().create(
            tableObject.getId(),
            tableObject.getResetpassToken(),
            tableObject.getExpiryDate(),
            userDataConverter.mapToDomain(tableObject.getUserDataMapper())
        );
    }
}
