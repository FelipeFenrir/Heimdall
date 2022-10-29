package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.commons.utils.GetterAndSetterTester;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
class EmailContentImplTest {

    private EmailContentImpl testObject;
    private GetterAndSetterTester tester;

    private final String template = "template";
    private final Map<String, Object> content = Map.of("chave", "valor");
    private final LocalDateTime createOn = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
    private final LocalDateTime updateOn = LocalDateTime.of(2022, 1, 1, 0, 0, 0);

    @BeforeEach
    public void setUp() {
        tester = new GetterAndSetterTester();
        testObject = EmailContentImpl.builder()
            .template(template)
            .content(content)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
    }

    @Test
    @DisplayName("[EmailContentImplTest] - Coverage the instance of domain class (Getter and Setter).")
    void coverageClassTheDomainPackage() {
        tester.testInstance(testObject);
    }

    @Test
    @DisplayName("[EmailContentImplTest] - Equals")
    void testEquals() {
        var instanceEquals = EmailContentImpl.builder()
            .template(template)
            .content(content)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
        assert instanceEquals.equals(testObject);
    }

    @Test
    @DisplayName("[EmailContentImplTest] - HashCode")
    void testHashCode() {
        var instanceHashCode = EmailContentImpl.builder()
            .template(template)
            .content(content)
            .createdOn(createOn)
            .updatedOn(updateOn)
            .build();
        var hashcodeTest = instanceHashCode.hashCode();

        Assertions.assertEquals(hashcodeTest, testObject.hashCode());

    }

    @Test
    @DisplayName("[EmailContentImplTest] - Builder")
    void builder() {
    }
}
