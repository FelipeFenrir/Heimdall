/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.dataprovider.service;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.RoleDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.RoleRepository;
import br.com.fenrir.auth.core.entity.domain.Role;
import br.com.fenrir.auth.core.usecase.ports.RoleGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
//@Service
@AllArgsConstructor
public class RoleService implements RoleGateway {

    //@Autowired
    private final RoleRepository roleRepository;

    //@Autowired
    private final RoleDataConverter roleDataConverter;

    @Override
    public Optional<Role> findByName(String rolename) {
        return Optional.of(roleDataConverter.mapToEntity(
                roleRepository.findByName(rolename).get()
        ));
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.of(roleDataConverter.mapToEntity(
                roleRepository.findById(id).get()
        ));
    }
}
