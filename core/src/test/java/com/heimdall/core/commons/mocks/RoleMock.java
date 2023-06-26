package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.RoleImpl;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@UtilityClass
public class RoleMock {
    public static UUID id = UUID.randomUUID();
    public static String roleName = "ROLE_TESTE";

    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static RoleImpl mockRole() {
        return RoleImpl.builder()
            .id(id)
            .name(roleName)
            .permission(Collections.singletonList(PermissionMock.mockPermission()))
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }
}
