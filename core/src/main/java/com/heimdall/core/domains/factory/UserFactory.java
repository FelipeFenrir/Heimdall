/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory;

import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.User;

import java.util.Collection;
import java.util.UUID;

/**
 * <p>
 *      Interface of {@link User} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface UserFactory {

    /**
     * <p>
     *      Create a {@link User} Domain Entity.
     * </p>
     * @param email E-mail of {@link User}.
     * @return {@link User} Domain Entity.
     */
    User create(String email, Collection<Role> roles);

    /**
     * <p>
     *      Create a {@link User} Domain Entity.
     * </p>
     * @param id ID of {@link User}.
     * @param name Name of {@link User}.
     * @param email E-mail of {@link User}.
     * @param password Password of {@link User}.
     * @param tenantId TenantID of {@link User}.
     * @param confirmationToken Is Token to Confirm Registration of {@link User}.
     * @param isEnabled Is {@link User} enabled.
     * @param isAcceptTerm Terms is acepted by {@link User}.
     * @param accountNonLocked Is {@link User} account is Locked.
     * @param accountNonExpired Is {@link User} account is Expired.
     * @param credentialsNonExpired  Is {@link User} credentials is Expired.
     * @param roles List of {@link Role} from {@link User}.
     * @return {@link User} Domain Entity.
     */
    User create(UUID id, String name, String email, String password, String tenantId, String confirmationToken,
                boolean isEnabled, boolean isAcceptTerm, boolean accountNonLocked, boolean accountNonExpired,
                boolean credentialsNonExpired, Collection<Role> roles);
}
