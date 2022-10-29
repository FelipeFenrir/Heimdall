/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters.impl;

import com.heimdall.converters.RepositoryConverter;
import com.heimdall.entity.ResetPasswordTokenDataEntity;

import com.heimdall.core.domains.model.ResetPasswordToken;
import com.heimdall.core.domains.model.implementations.ResetPasswordTokenImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *     Converter from {@link ResetPasswordToken} Domain.
 * </p>
 * <p>
 *     Convert {@link ResetPasswordToken} to {@link ResetPasswordTokenDataEntity}.
 *     Convert {@link ResetPasswordTokenDataEntity} to {@link ResetPasswordToken}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class ResetPasswordTokenDataConverter implements RepositoryConverter<ResetPasswordTokenDataEntity,
        ResetPasswordToken> {

    @Autowired
    private UserDataConverter userDataConverter;

    @Override
    public ResetPasswordTokenDataEntity mapToRepository(ResetPasswordToken domainObject) {
        return ResetPasswordTokenDataEntity.builder()
                .id(domainObject.getId())
                .resetpassToken(domainObject.getResetPassToken())
                .expiryDate(domainObject.getExpiryDate())
                .userDataMapper(userDataConverter.mapToRepository(domainObject.getUser()))
                .build();
    }

    @Override
    public ResetPasswordToken mapToEntity(ResetPasswordTokenDataEntity tableObject) {
        return ResetPasswordTokenImpl.builder()
                .id(tableObject.getId())
                .resetPassToken(tableObject.getResetpassToken())
                .expiryDate(tableObject.getExpiryDate())
                .user(userDataConverter.mapToEntity(tableObject.getUserDataMapper()))
                .build();
    }
}
