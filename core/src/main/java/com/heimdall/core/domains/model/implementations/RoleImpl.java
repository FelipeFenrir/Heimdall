/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.domains.model.Permission;
import com.heimdall.core.domains.model.Role;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * <p>
 *      Domain {@link Role} Implementation Class.
 *      This class has the Design Pattern Builder implemented using Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RoleImpl implements Role {
    private UUID id;
    @NotNull
    @NotEmpty
    private String name;
    private Collection<Permission> permission;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
