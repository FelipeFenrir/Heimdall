/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory.implementations;

import com.heimdall.core.domains.model.ResetPasswordToken;
import com.heimdall.core.domains.model.User;
import com.heimdall.core.domains.model.implementations.ResetPasswordTokenImpl;
import com.heimdall.core.domains.factory.ResetPasswordTokenFactory;

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
    public ResetPasswordToken create(UUID id, String token, LocalDateTime expiryDate, User user) {
        return ResetPasswordTokenImpl.builder()
                .id(id)
                .resetPassToken(token)
                .expiryDate(expiryDate)
                .user(user)
                .build();
    }
}
