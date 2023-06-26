package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.commons.mocks.PermissionMock;
import com.heimdall.core.commons.utils.GetterAndSetterTester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionImplTest {

    private PermissionImpl testObject;
    private GetterAndSetterTester tester;

    @BeforeEach
    void setUp() {
        tester = new GetterAndSetterTester();
        testObject = PermissionMock.mockPermission();
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
