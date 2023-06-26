/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.converters.impl.UserDataConverter;
import com.heimdall.repository.UserRepository;

import com.heimdall.core.domains.model.User;
import com.heimdall.ports.datastore.UserGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class UserService implements UserGateway {

    private final UserRepository userRepository;

    private final UserDataConverter userDataConverter;

    @Override
    public User save(User user) {
        return userDataConverter.mapToDomain(userRepository.save(userDataConverter.mapToRepository(user)));
    }

    @Override
    public void delete(User user) {
        userRepository.delete(userDataConverter.mapToRepository(user));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.of(userDataConverter.mapToDomain(
                userRepository.findById(id).get()
        ));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userDataConverter.mapToDomain(
                userRepository.findByEmail(email).get()
        ));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(userDataConverter.mapToDomain(
                userRepository.findByUsername(username).get()
        ));
    }

    @Override
    public Optional<User> findByConfirmationToken(String token) {
        return Optional.of(userDataConverter.mapToDomain(
                userRepository.findByConfirmationToken(token).get()
        ));
    }
}
