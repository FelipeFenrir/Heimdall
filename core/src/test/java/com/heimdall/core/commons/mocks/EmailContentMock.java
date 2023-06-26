package com.heimdall.core.commons.mocks;

import com.heimdall.core.domains.model.implementations.EmailContentImpl;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Map;

@UtilityClass
public class EmailContentMock {
    public static final String template = "template";
    public static final Map<String, Object> content = Map.of("chave", "valor");
    public static final LocalDateTime createOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);
    public static final LocalDateTime updateOn = LocalDateTime.of(
        2022, 1, 1, 0, 0, 0);

    public static EmailContentImpl mockEmailTemplate() {
        return EmailContentImpl.builder()
            .template(template)
            .content(content)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }
}
