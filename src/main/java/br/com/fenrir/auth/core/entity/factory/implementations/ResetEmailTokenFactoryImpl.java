/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory.implementations;

import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.factory.ResetEmailTokenFactory;
import br.com.fenrir.auth.core.entity.domain.ResetEmailToken;
import br.com.fenrir.auth.core.entity.domain.implementations.ResetEmailTokenImpl;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *      Domain {@link ResetEmailToken} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class ResetEmailTokenFactoryImpl implements ResetEmailTokenFactory {
    @Override
    public ResetEmailToken create(String pendingEmail, long minutesToExpiry, User user) {
        ResetEmailToken resetEmailToken = ResetEmailTokenImpl.builder()
                .pendingEmail(pendingEmail)
                .resetEmailToken(UUID.randomUUID().toString())
                .user(user)
                .build();
        resetEmailToken.setExpiryDate(minutesToExpiry);
        return resetEmailToken;
    }

    @Override
    public ResetEmailToken create(Long id, String pendingEmail, String token, LocalDateTime expiryDate, User user) {
        return ResetEmailTokenImpl.builder()
                .id(id)
                .pendingEmail(pendingEmail)
                .resetEmailToken(token)
                .expiryDate(expiryDate)
                .user(user)
                .build();
    }
}
