package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.PermissionImpl;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class PermissionMock {
    public static final UUID id = UUID.randomUUID();
    public static final String permissionName = "PERM_TESTE";

    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static PermissionImpl mockPermission() {
        return PermissionImpl.builder()
            .id(id)
            .name(permissionName)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }
}
