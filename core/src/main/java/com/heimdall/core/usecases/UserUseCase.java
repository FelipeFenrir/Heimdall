/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.usecases;

import com.heimdall.core.domains.model.User;
import com.heimdall.ports.command.UserCommand;
import com.heimdall.core.exceptions.UserNotFoundException;
import com.heimdall.ports.datastore.UserGateway;

import lombok.AllArgsConstructor;

import java.util.Optional;

/**
 * <p>
 *     {@link User} use case implementation.
 *     This class implements the Facade Design Pattern.
 * </p>
 * @author Felipe de Andrade Batista
 */
@AllArgsConstructor
public class UserUseCase implements UserCommand {

    private final UserGateway userGateway;

    @Override
    public User getUser(String username) throws UserNotFoundException {
        //log.debug("Get requested for: {}", username);
        Optional<User> optionalUser = userGateway.findByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        return optionalUser.get();
    }

    @Override
    public User getUserByEmail(String usermail) throws UserNotFoundException {
        //log.debug("Get requested for: {}", username);
        Optional<User> optionalUser = userGateway.findByEmail(usermail);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        return optionalUser.get();
    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException {
        //log.debug("Deletion requested for: {}", username);
        userGateway.delete(getUser(username));
    }
}
