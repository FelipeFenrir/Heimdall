package com.heimdall.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthorizationRepositoryTest {

    @Test
    void findByState() {
    }

    @Test
    void findByAuthorizationCodeValue() {
    }

    @Test
    void findByAccessTokenValue() {
    }

    @Test
    void findByRefreshTokenValue() {
    }

    @Test
    void findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue() {
    }
}
