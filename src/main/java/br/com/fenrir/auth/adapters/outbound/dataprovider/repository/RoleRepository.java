/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.repository;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.RoleDataMapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 *      {@link RoleDataMapper} Repository interface.
 *      Uses Spring JPA repository.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleDataMapper, Long>, JpaSpecificationExecutor<RoleDataMapper> {

    /**
     * Find a role by name.
     *
     * @param name the role's rolename
     * @return RoleDataMapper which contains the role with the given rolename or null.
     */
    Optional<RoleDataMapper> findByName(String name);

    /**
     * Find a role by ID.
     *
     * @param id the role's ID
     * @return RoleDataMapper returns an Optional RoleDataMapper object which contains the role or
     * null.
     */
    @Override
    Optional<RoleDataMapper> findById(Long id);
}
