/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.converters.impl.ResetEmailTokenDataConverter;
import com.heimdall.repository.ResetEmailTokenRepository;

import com.heimdall.core.domains.model.ResetEmailToken;
import com.heimdall.ports.datastore.ResetEmailTokenGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
