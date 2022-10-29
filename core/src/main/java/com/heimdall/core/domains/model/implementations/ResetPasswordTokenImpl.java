/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.domains.model.ResetPasswordToken;
import com.heimdall.core.domains.model.User;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *      Domain {@link ResetPasswordToken} Implementation Class.
 *      This class has the Design Pattern Builder implemented using Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ResetPasswordTokenImpl implements ResetPasswordToken {

    private UUID id;
    @NotNull
    @NotEmpty
    private String resetPassToken;
    private LocalDateTime expiryDate;
    @NotNull
    @NotEmpty
    private User user;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @Override
    public void setExpiryDate(long minutes) {
        this.expiryDate = LocalDateTime.now().plusMinutes(minutes);
    }

    @Override
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }
}
