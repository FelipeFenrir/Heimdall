package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.UserImpl;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@UtilityClass
public class UserMock {
    public static final UUID id = UUID.randomUUID();
    public static final String email = "meuemail@teste.com";
    public static final String username = "usuario";
    public static final String password = "minhasenha";
    public static final String tenantId = "tenantteste";
    public static final String confirmationToken = "token_de_confirmacao";

    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static UserImpl mockUser() {
        return UserImpl.builder()
            .id(id)
            .email(email)
            .username(username)
            .password(password)
            .tenantid(tenantId)
            .role(Collections.singletonList(RoleMock.mockRole()))
            .acceptTerm(true)
            .pendingEmail(email)
            .confirmationToken(confirmationToken)
            .accountNonExpired(false)
            .accountNonLocked(false)
            .credentialsNonExpired(false)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .enabled(true)
            .build();
    }
}
