package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.commons.annotation.Unitario;
import com.heimdall.core.commons.mocks.EmailContentMock;
import com.heimdall.core.commons.utils.GetterAndSetterTester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Unitario
class EmailContentImplTest {

    private EmailContentImpl testObject;
    private GetterAndSetterTester tester;


    @BeforeEach
    public void setUp() {
        tester = new GetterAndSetterTester();
        testObject = EmailContentMock.mockEmailTemplate();
    }

    @Test
    @DisplayName("[Core][Model][EmailContentImplTest] - Coverage the instance of domain class (Getter and Setter).")
    void coverageClassTheDomainPackage() {
        tester.testInstance(testObject);
    }

    @Test
    @DisplayName("[Core][Model][EmailContentImplTest] - Equals")
    void testEquals() {
        var instanceEquals = EmailContentImpl.builder()
            .template(EmailContentMock.template)
            .content(EmailContentMock.content)
            .createdOn(EmailContentMock.createOn)
            .updatedOn(EmailContentMock.updateOn)
            .build();
        assert instanceEquals.equals(testObject);
    }

    @Test
    @DisplayName("[Core][Model][EmailContentImplTest] - HashCode")
    void testHashCode() {
        var instanceHashCode = EmailContentImpl.builder()
            .template(EmailContentMock.template)
            .content(EmailContentMock.content)
            .createdOn(EmailContentMock.createOn)
            .updatedOn(EmailContentMock.updateOn)
            .build();
        var hashcodeTest = instanceHashCode.hashCode();

        Assertions.assertEquals(hashcodeTest, testObject.hashCode());

    }

    @Test
    @DisplayName("[Core][Model][EmailContentImplTest] - Builder")
    void builder() {
        var instanceBuilder = EmailContentImpl.builder()
            .template(EmailContentMock.template)
            .content(EmailContentMock.content)
            .createdOn(EmailContentMock.createOn)
            .updatedOn(EmailContentMock.updateOn)
            .build();
        tester.testInstance(instanceBuilder);
    }
}
