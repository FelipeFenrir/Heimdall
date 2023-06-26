/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.User;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *      Domain {@link User} Implementation Class.
 *      This class has the Design Pattern Builder implemented using Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserImpl implements User {
    private UUID id;
    @NotNull
    @NotEmpty
    private String username;
    @Email
    @NotNull
    @NotEmpty
    private String email;
    private String pendingEmail;
    @NotNull
    @NotEmpty
    private String password;
    private String tenantid;
    private String confirmationToken;
    @NotNull
    @Builder.Default
    private boolean enabled = false;
    @NotNull
    @Builder.Default
    private boolean acceptTerm = false;
    @NotNull
    @Builder.Default
    private boolean accountNonLocked = false;
    @NotNull
    @Builder.Default
    private boolean accountNonExpired = false;
    @NotNull
    @Builder.Default
    private boolean credentialsNonExpired = false;
    private Collection<Role> role;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @Override
    public boolean isConfirmationTokenExpired(long minutes) {
        return LocalDateTime.now().isAfter(this.createdOn.plusMinutes(minutes));
    }
}
