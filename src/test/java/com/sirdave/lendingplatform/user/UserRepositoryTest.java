package com.sirdave.lendingplatform.user;

import com.sirdave.lendingplatform.util.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void isUserWithEmailExist() {
        String email = "davidabiola@gmail.com";
        User user = new User(
                "David",
                "Abiola",
                email,
                "+2348101310757",
                "@Dave",
                LocalDate.now(),
                Role.ROLE_USER.name(),
                Role.ROLE_USER.getAuthorities(),
                true,
                true
        );

        repository.save(user);

        Optional<User> optionalUser = repository.findUserByEmail(email);
        assertThat(optionalUser).isNotEmpty();
    }

    @Test
    void isUserWithPhoneNumberExist() {
        String phoneNumber = "+2348101310757";
        User user = new User(
                "David",
                "Abiola",
                "davidabiola@gmail.com",
                phoneNumber,
                "@Dave",
                LocalDate.now(),
                Role.ROLE_USER.name(),
                Role.ROLE_USER.getAuthorities(),
                true,
                true
        );

        repository.save(user);

        Optional<User> optionalUser = repository.findUserByPhoneNumber(phoneNumber);
        assertThat(optionalUser).isNotEmpty();
    }

    @Test
    void isUserWithPhoneNumberDoesNotExist() {
        String phoneNumber = "+2348101310757";
        Optional<User> optionalUser = repository.findUserByPhoneNumber(phoneNumber);
        assertThat(optionalUser).isEmpty();
    }


}