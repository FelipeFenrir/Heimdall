/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.service;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.ResetEmailTokenDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.ResetEmailTokenRepository;
import br.com.fenrir.auth.core.entity.domain.ResetEmailToken;
import br.com.fenrir.auth.core.usecase.ports.ResetEmailTokenGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *      Reset Email Service implementation.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
//@Service
@AllArgsConstructor
public class ResetEmailTokenService implements ResetEmailTokenGateway {

    //@Autowired
    private ResetEmailTokenRepository resetEmailTokenRepository;

    //@Autowired
    private ResetEmailTokenDataConverter resetEmailTokenDataConverter;

    @Override
    public ResetEmailToken save(ResetEmailToken resetEmailToken) {
        return resetEmailTokenDataConverter.mapToEntity(
                resetEmailTokenRepository.save(
                        resetEmailTokenDataConverter.mapToRepository(resetEmailToken)
                ));
    }

    @Override
    public void delete(ResetEmailToken resetEmailToken) {
        resetEmailTokenRepository.delete(resetEmailTokenDataConverter.mapToRepository(resetEmailToken));
    }

    @Override
    public Optional<ResetEmailToken> findByToken(String resetEmailToken) {
        return Optional.of(resetEmailTokenDataConverter.mapToEntity(
                resetEmailTokenRepository.findByResetEmailToken(resetEmailToken).get()
        ));
    }
}
