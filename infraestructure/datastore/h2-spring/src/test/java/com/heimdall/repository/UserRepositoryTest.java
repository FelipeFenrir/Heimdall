package com.heimdall.repository;

import com.heimdall.mock.UserDataMock;

import jakarta.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    @DisplayName("[] - ")
    public void givenUser_whenSave_thenOk() {

        var user = UserDataMock.newValid();

        final var listOfUsersId = new java.util.ArrayList<>();

        // the ids of prod db entities are reserved
        userRepository.findAll().forEach(userData -> listOfUsersId.add(userData.getId()));

        // the id keeps incrementing between runs
        var newUserId = userRepository.save(user).getId();

        Assertions.assertFalse(listOfUsersId.contains(newUserId));
    }

    @Test
    @DisplayName("[] - ")
    void givenUser_whenfindByUsername_thenOk() {
        var mock = UserDataMock.newValid();

        userRepository.save(mock);

        final var user = userRepository.findByUsername(mock.getUsername());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(user.get().getUsername(), mock.getUsername());
    }

    @Test
    @DisplayName("[] - ")
    void givenUser_whenfindByEmail_thenOk() {
        var mock = UserDataMock.newValid();

        userRepository.save(mock);

        final var user = userRepository.findByEmail(mock.getEmail());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(user.get().getEmail(), mock.getEmail());
    }

    @Test
    @DisplayName("[] - ")
    void givenUser_whenfindByConfirmationToken_thenOk() {
        var mock = UserDataMock.newValid();

        userRepository.save(mock);

        final var user = userRepository.findByConfirmationToken(mock.getConfirmationToken());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(user.get().getConfirmationToken(), mock.getConfirmationToken());
    }

    @Test
    @DisplayName("[] - ")
    void givenUser_whenfindById_thenOk() {
        var mock = UserDataMock.newValid();

        userRepository.save(mock);

        final var user = userRepository.findById(mock.getId());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(user.get().getId(), mock.getId());
    }
}
