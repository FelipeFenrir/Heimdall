package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.PermissionImpl;
import com.heimdall.core.domains.model.implementations.ResetEmailTokenImpl;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class ResetEmailTokenMock {
    public static final UUID id = UUID.randomUUID();
    public static final String email = "meuemail@teste.com";

    public static final LocalDateTime expiryDate = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static ResetEmailTokenImpl mockResetEmailToken() {
        return ResetEmailTokenImpl.builder()
            .id(id)
            .user(UserMock.mockUser())
            .resetEmailToken(email)
            .pendingEmail(email)
            .expiryDate(expiryDate)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }
}
