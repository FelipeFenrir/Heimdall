/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.converters.impl.RoleDataConverter;
import com.heimdall.repository.RoleRepository;

import com.heimdall.core.domains.model.Role;
import com.heimdall.ports.datastore.RoleGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class RoleService implements RoleGateway {

    private final RoleRepository roleRepository;

    private final RoleDataConverter roleDataConverter;

    @Override
    public Optional<Role> findByName(String rolename) {
        return Optional.of(roleDataConverter.mapToDomain(
                roleRepository.findByName(rolename).get()
        ));
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return Optional.of(roleDataConverter.mapToDomain(
                roleRepository.findById(id).get()
        ));
    }
}
