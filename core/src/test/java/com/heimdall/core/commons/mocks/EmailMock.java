package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.EmailImpl;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class EmailMock {
    public static final String to = "fulano@teste.com";
    public static final String from = "siclano@teste.com";
    public static final String subject = "Teste";

    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static EmailImpl mockEmail() {
        return EmailImpl.builder()
            .to(to)
            .from(from)
            .subject(subject)
            .emailContent(EmailContentMock.mockEmailTemplate())
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }
}
