package com.heimdall.repository;

import com.heimdall.mock.RoleDataMock;

import jakarta.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class RoleRepositoryTest {

    @Resource
    private RoleRepository roleRepository;

    @Test
    @DisplayName("[] - ")
    public void givenRole_whenSave_thenOk() {

        var role = RoleDataMock.newValidMaster();

        final var listOfRolesId = new java.util.ArrayList<>();

        // the ids of prod db entities are reserved
        roleRepository.findAll().forEach(permissionData -> listOfRolesId.add(permissionData.getId()));

        // the id keeps incrementing between runs
        var newRoleId = roleRepository.save(role).getId();

        Assertions.assertFalse(listOfRolesId.contains(newRoleId));
    }

    @Test
    @DisplayName("[] - ")
    void givenRole_whenfindByName_thenOk() {
        var mock = RoleDataMock.newValidMaster();

        roleRepository.save(mock);

        final var role = roleRepository.findByName(mock.getName());
        Assertions.assertTrue(role.isPresent());
        Assertions.assertEquals(role.get().getName(), mock.getName());
    }

    @Test
    @DisplayName("[] - ")
    void givenRole_whenfindById_thenOk() {
        var mock = RoleDataMock.newValidMaster();

        roleRepository.save(mock);

        final var role = roleRepository.findById(mock.getId());
        Assertions.assertTrue(role.isPresent());
        Assertions.assertEquals(role.get().getId(), mock.getId());
    }
}
