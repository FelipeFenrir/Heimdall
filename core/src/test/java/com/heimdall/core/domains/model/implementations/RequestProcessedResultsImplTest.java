package com.heimdall.core.domains.model.implementations;

import com.heimdall.core.commons.mocks.RequestProcessedResultsMock;
import com.heimdall.core.commons.utils.GetterAndSetterTester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestProcessedResultsImplTest {

    private RequestProcessedResultsImpl testObject;
    private GetterAndSetterTester tester;

    @BeforeEach
    void setUp() {
        tester = new GetterAndSetterTester();
        testObject = mockRequestProcessedResultsImpl();
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    public static RequestProcessedResultsImpl mockRequestProcessedResultsImpl() {
        return new RequestProcessedResultsImpl(
            RequestProcessedResultsMock.operatingSystem,
            RequestProcessedResultsMock.browserName,
            RequestProcessedResultsMock.ipaddress,
            RequestProcessedResultsMock.uri);
    }
}
