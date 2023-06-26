/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory.implementations;

import com.heimdall.core.domains.model.ResetEmailToken;
import com.heimdall.core.domains.model.User;
import com.heimdall.core.domains.model.implementations.ResetEmailTokenImpl;
import com.heimdall.core.domains.factory.ResetEmailTokenFactory;

import java.time.LocalDateTime;
import java.util.UUID;

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
    public ResetEmailToken create(UUID id, String pendingEmail, String token, LocalDateTime expiryDate, User user) {
        return ResetEmailTokenImpl.builder()
                .id(id)
                .pendingEmail(pendingEmail)
                .resetEmailToken(token)
                .expiryDate(expiryDate)
                .user(user)
                .build();
    }
}
