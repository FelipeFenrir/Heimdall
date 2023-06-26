/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.configuration.security.service;

import com.heimdall.core.domains.model.User;
import com.heimdall.entrypoint.converters.implementations.UserDeliveryConverter;
import com.heimdall.ports.command.UserCommand;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@AllArgsConstructor
@Service(value = "userDetailsService")
public class UserDetailedService implements UserDetailsService {

    private final UserCommand userCommand;

    private final UserDeliveryConverter userDeliveryConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user;

        if (emailValido(username)) {
            log.info("Loading user by email: {}", username);
            user = userCommand.getUserByEmail(username);
        } else {
            log.info("Loading user by username: {}", username);
            user = userCommand.getUser(username);
        }

        if (Objects.isNull(user)) {
            log.error("User not found with credential: {}", username);
            throw new UsernameNotFoundException("Login / E-mail not found.");
        }
        return userDeliveryConverter.mapToBoundary(user);
    }

    private boolean emailValido(String email) {
        final var EMAIL_PATTERN =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if (Objects.isNull(email) || email.isBlank()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
