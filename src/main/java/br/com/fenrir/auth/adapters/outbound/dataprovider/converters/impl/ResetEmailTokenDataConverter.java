/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.RepositoryConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.ResetEmailTokenDataMapper;
import br.com.fenrir.auth.core.entity.domain.ResetEmailToken;
import br.com.fenrir.auth.core.entity.domain.implementations.ResetEmailTokenImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *     Converter from {@link ResetEmailToken} Domain.
 * </p>
 * <p>
 *     Convert {@link ResetEmailToken} to {@link ResetEmailTokenDataMapper}.
 *     Convert {@link ResetEmailTokenDataMapper} to {@link ResetEmailToken}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class ResetEmailTokenDataConverter implements RepositoryConverter<ResetEmailTokenDataMapper, ResetEmailToken> {

    @Autowired
    private UserDataConverter userDataConverter;

    @Override
    public ResetEmailTokenDataMapper mapToRepository(ResetEmailToken domainObject) {
        return ResetEmailTokenDataMapper.builder()
                .id(domainObject.getId())
                .pendingEmail(domainObject.getPendingEmail())
                .resetEmailToken(domainObject.getResetEmailToken())
                .expiryDate(domainObject.getExpiryDate())
                .userDataMapper(userDataConverter.mapToRepository(domainObject.getUser()))
                .build();
    }

    @Override
    public ResetEmailToken mapToEntity(ResetEmailTokenDataMapper tableObject) {
        return ResetEmailTokenImpl.builder()
                .id(tableObject.getId())
                .pendingEmail(tableObject.getPendingEmail())
                .resetEmailToken(tableObject.getResetEmailToken())
                .expiryDate(tableObject.getExpiryDate())
                .user(userDataConverter.mapToEntity(tableObject.getUserDataMapper()))
                .build();
    }
}
