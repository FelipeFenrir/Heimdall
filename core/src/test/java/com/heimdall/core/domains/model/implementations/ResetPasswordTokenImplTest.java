package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.commons.mocks.ResetPasswordTokenMock;
import com.heimdall.core.commons.utils.GetterAndSetterTester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResetPasswordTokenImplTest {

    private ResetPasswordTokenImpl testObject;
    private GetterAndSetterTester tester;

    @BeforeEach
    void setUp() {
        tester = new GetterAndSetterTester();
        testObject = ResetPasswordTokenMock.mockResetPasswordToken();
    }

    @Test
    void isExpired() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void builder() {
    }
}
