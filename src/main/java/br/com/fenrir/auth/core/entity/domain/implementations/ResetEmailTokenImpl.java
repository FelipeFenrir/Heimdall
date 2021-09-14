/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain.implementations;

import br.com.fenrir.auth.core.entity.domain.ResetEmailToken;
import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.util.SelfValidating;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 *      Domain {@link ResetEmailToken} Implementation Class.
 *      This class has the Design Pattern Builder implemented using Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ResetEmailTokenImpl extends SelfValidating<ResetEmailTokenImpl> implements ResetEmailToken {

    private long id;
    @NotNull
    @NotEmpty
    @Email
    private String pendingEmail;
    @NotNull
    @NotEmpty
    private String resetEmailToken;
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
