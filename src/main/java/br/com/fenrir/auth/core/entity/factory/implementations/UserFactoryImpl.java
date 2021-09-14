/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory.implementations;

import br.com.fenrir.auth.core.entity.domain.Role;
import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.entity.domain.implementations.UserImpl;
import br.com.fenrir.auth.core.entity.factory.UserFactory;

import java.util.Collection;
import java.util.UUID;

/**
 * <p>
 *      Domain {@link User} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class UserFactoryImpl implements UserFactory {

    @Override
    public User create(String email, Collection<Role> roles) {
        return UserImpl.builder()
                // Generate a default username.
                .username(User.generateDefaultUsernameByEmail(email))
                .email(email)
                .password(User.generateDefaultPassword())
                .role(roles)
                // Generate random 36-character token for confirmation link.
                .confirmationToken(UUID.randomUUID().toString())
                //.enabled(false)
                //.acceptTerm(false)
                //.accountNonLocked(false)
                //.accountNonExpired(false)
                //.credentialsNonExpired(false)
                .build();
    }

    @Override
    public User create(long id, String name, String email, String password, String tenantId, String confirmationToken,
                       boolean isEnabled, boolean isAcceptTerm, boolean accountNonLocked, boolean accountNonExpired,
                       boolean credentialsNonExpired, Collection<Role> roles) {
        return UserImpl.builder()
                .id(id)
                .username(name)
                .email(email)
                .password(password)
                .tenantid(tenantId)
                .confirmationToken(confirmationToken)
                .enabled(isEnabled)
                .acceptTerm(isAcceptTerm)
                .accountNonLocked(accountNonLocked)
                .accountNonExpired(accountNonExpired)
                .credentialsNonExpired(credentialsNonExpired)
                .role(roles)
                .build();
    }
}
