package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.ResetPasswordTokenImpl;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class ResetPasswordTokenMock {
    public UUID id = UUID.randomUUID();
    public static final String resetPassToken = "token_de_reset_senha";

    public static final LocalDateTime expiryDate = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static ResetPasswordTokenImpl mockResetPasswordToken() {
        return ResetPasswordTokenImpl.builder()
            .id(id)
            .user(UserMock.mockUser())
            .resetPassToken(resetPassToken)
            .expiryDate(expiryDate)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }
}
