/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.service;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.ResetPasswordTokenDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.ResetPasswordTokenRepository;
import br.com.fenrir.auth.core.entity.domain.ResetPasswordToken;
import br.com.fenrir.auth.core.usecase.ports.ResetPasswordTokenGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *      Reset Password Service implementation.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
//@Service
@AllArgsConstructor
public class ResetPasswordTokenService implements ResetPasswordTokenGateway {

    //@Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    //@Autowired
    private ResetPasswordTokenDataConverter resetPasswordTokenDataConverter;

    @Override
    public ResetPasswordToken save(ResetPasswordToken resetPasswordToken) {
        return resetPasswordTokenDataConverter.mapToEntity(
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
        return Optional.of(resetPasswordTokenDataConverter.mapToEntity(
                resetPasswordTokenRepository.findByResetPassToken(resetPassToken).get()
        ));
    }
}
