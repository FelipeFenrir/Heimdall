/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.implementations;

import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.usecase.exceptions.UserNotFoundException;
import br.com.fenrir.auth.core.usecase.ports.TokenGateway;
import br.com.fenrir.auth.core.usecase.UserUseCase;
import br.com.fenrir.auth.core.usecase.ports.UserGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * <p>
 *     {@link User} use case implementation.
 *     This class implements the Facade Design Pattern.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
@AllArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserGateway userGateway;
    private final TokenGateway tokenGateway;

    @Override
    public User getUser(String username) throws UserNotFoundException {
        log.debug("Get requested for: {}", username);
        Optional<User> optionalUser = userGateway.findByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        return optionalUser.get();
    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException {
        log.debug("Deletion requested for: {}", username);
        userGateway.delete(getUser(username));
    }

    @Override
    public void revokeAllTokens(String username) {
        log.debug("Revoke all tokens for: {}", username);
        tokenGateway.revokeTokens(username);
    }
}
