package com.heimdall.repository;

import com.heimdall.mock.PermissionDataMock;

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
class PermissionRepositoryTest {

    @Resource
    private PermissionRepository permissionRepository;

    @Test
    @DisplayName("[] - ")
    public void givenPermission_whenSave_thenOk() {

        var permission = PermissionDataMock.newValid();

        final var listOfPermissionsId = new java.util.ArrayList<>();

        // the ids of prod db entities are reserved
        permissionRepository.findAll().forEach(permissionData -> listOfPermissionsId.add(permissionData.getId()));

        // the id keeps incrementing between runs
        var newPermissionId = permissionRepository.save(permission).getId();

        Assertions.assertFalse(listOfPermissionsId.contains(newPermissionId));
    }

    @Test
    @DisplayName("[] - ")
    void givenPermission_whenfindById_thenOk() {
        var mock = PermissionDataMock.newValid();

        permissionRepository.save(mock);

        final var permission = permissionRepository.findById(mock.getId());
        Assertions.assertTrue(permission.isPresent());
        Assertions.assertEquals(permission.get().getId(), mock.getId());
    }
}
