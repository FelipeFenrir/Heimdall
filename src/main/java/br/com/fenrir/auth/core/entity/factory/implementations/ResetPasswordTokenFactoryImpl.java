/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory.implementations;

import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.factory.ResetPasswordTokenFactory;
import br.com.fenrir.auth.core.entity.domain.ResetPasswordToken;
import br.com.fenrir.auth.core.entity.domain.implementations.ResetPasswordTokenImpl;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *      Domain {@link ResetPasswordToken} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class ResetPasswordTokenFactoryImpl implements ResetPasswordTokenFactory {

    @Override
    public ResetPasswordToken create(long minutesToExpiry, User user) {
        ResetPasswordToken resetPasswordToken = ResetPasswordTokenImpl.builder()
                .resetPassToken(UUID.randomUUID().toString())
                .user(user)
                .build();
        resetPasswordToken.setExpiryDate(minutesToExpiry);
        return resetPasswordToken;
    }

    @Override
    public ResetPasswordToken create(Long id, String token, LocalDateTime expiryDate, User user) {
        return ResetPasswordTokenImpl.builder()
                .id(id)
                .resetPassToken(token)
                .expiryDate(expiryDate)
                .user(user)
                .build();
    }
}
