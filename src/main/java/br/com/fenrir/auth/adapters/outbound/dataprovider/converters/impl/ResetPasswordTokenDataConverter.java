/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.RepositoryConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.ResetPasswordTokenDataMapper;
import br.com.fenrir.auth.core.entity.domain.ResetPasswordToken;
import br.com.fenrir.auth.core.entity.domain.implementations.ResetPasswordTokenImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *     Converter from {@link ResetPasswordToken} Domain.
 * </p>
 * <p>
 *     Convert {@link ResetPasswordToken} to {@link ResetPasswordTokenDataMapper}.
 *     Convert {@link ResetPasswordTokenDataMapper} to {@link ResetPasswordToken}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class ResetPasswordTokenDataConverter implements RepositoryConverter<ResetPasswordTokenDataMapper,
        ResetPasswordToken> {

    @Autowired
    private UserDataConverter userDataConverter;

    @Override
    public ResetPasswordTokenDataMapper mapToRepository(ResetPasswordToken domainObject) {
        return ResetPasswordTokenDataMapper.builder()
                .id(domainObject.getId())
                .resetpassToken(domainObject.getResetPassToken())
                .expiryDate(domainObject.getExpiryDate())
                .userDataMapper(userDataConverter.mapToRepository(domainObject.getUser()))
                .build();
    }

    @Override
    public ResetPasswordToken mapToEntity(ResetPasswordTokenDataMapper tableObject) {
        return ResetPasswordTokenImpl.builder()
                .id(tableObject.getId())
                .resetPassToken(tableObject.getResetpassToken())
                .expiryDate(tableObject.getExpiryDate())
                .user(userDataConverter.mapToEntity(tableObject.getUserDataMapper()))
                .build();
    }
}
