/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.converters.impl.ResetPasswordTokenDataConverter;
import com.heimdall.repository.ResetPasswordTokenRepository;

import com.heimdall.core.domains.model.ResetPasswordToken;
import com.heimdall.ports.datastore.ResetPasswordTokenGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class ResetPasswordTokenService implements ResetPasswordTokenGateway {

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    private final ResetPasswordTokenDataConverter resetPasswordTokenDataConverter;

    @Override
    public ResetPasswordToken save(ResetPasswordToken resetPasswordToken) {
        return resetPasswordTokenDataConverter.mapToDomain(
                resetPasswordTokenRepository.save(
                        resetPasswordTokenDataConverter.mapToRepository(resetPasswordToken)
                ));
    }

    @Override
    public void delete(ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenRepository.delete(resetPasswordTokenDataConverter.mapToRepository(resetPasswordToken));
    }

    @Override
    public Optional<ResetPasswordToken> findByToken(String resetPassToken) {
        return Optional.of(resetPasswordTokenDataConverter.mapToDomain(
                resetPasswordTokenRepository.findByResetpassToken(resetPassToken).get()
        ));
    }
}
