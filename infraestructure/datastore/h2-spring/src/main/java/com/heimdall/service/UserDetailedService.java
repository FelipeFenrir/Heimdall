/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.entity.UserDataEntity;
import com.heimdall.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *      {@link UserDetailsService} implementation.
 *      Needs to implement since it is injected into OAuth2 configuration.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Service(value = "userDetailsService")
public class UserDetailedService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDataMapper> optionalUser;

        if (ValidationUtil.emailValido(username)) {
            log.info("Loading user by email: {}", username);
            optionalUser = userRepository.findByEmail(username);
        } else {
            log.info("Loading user by username: {}", username);
            optionalUser = userRepository.findByUsername(username);
        }

        if (!optionalUser.isPresent()) {
            log.error("User not found with credential: {}", username);
            throw new UsernameNotFoundException("Login / E-mail not found.");
//            throw new BadCredentialsException("Bad credentials");
        }
//        new AccountStatusUserDetailsChecker().check(optionalUser.get());

        return optionalUser.get();
    }
}
