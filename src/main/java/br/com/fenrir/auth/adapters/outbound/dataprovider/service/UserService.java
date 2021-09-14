/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.service;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.UserDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.UserRepository;
import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.usecase.ports.UserGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
//@Service
@AllArgsConstructor
public class UserService implements UserGateway {

    //@Autowired
    private UserRepository userRepository;

    //@Autowired
    private UserDataConverter userDataConverter;

    @Override
    public User save(User user) {
        return userDataConverter.mapToEntity(userRepository.save(userDataConverter.mapToRepository(user)));
    }

    @Override
    public void delete(User user) {
        userRepository.delete(userDataConverter.mapToRepository(user));
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.of(userDataConverter.mapToEntity(
                userRepository.findById(id).get()
        ));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userDataConverter.mapToEntity(
                userRepository.findByEmail(email).get()
        ));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(userDataConverter.mapToEntity(
                userRepository.findByUsername(username).get()
        ));
    }

    @Override
    public Optional<User> findByConfirmationToken(String token) {
        return Optional.of(userDataConverter.mapToEntity(
                userRepository.findByConfirmationToken(token).get()
        ));
    }
}
