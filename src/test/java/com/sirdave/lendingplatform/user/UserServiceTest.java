package com.sirdave.lendingplatform.user;

import com.sirdave.lendingplatform.account.AccountService;
import com.sirdave.lendingplatform.exception.PasswordsDoNotMatchException;
import com.sirdave.lendingplatform.exception.UserExistsException;
import com.sirdave.lendingplatform.exception.UserNotFoundException;
import com.sirdave.lendingplatform.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;
    @Mock private UserRepository repository;
    @Mock private AccountService accountService;



    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(repository, accountService);
    }


    @Test
    void canRegisterUser() throws PasswordsDoNotMatchException, UserExistsException {
        //given
        User user = new User(
                "David",
                "Abiola",
                "davidabiola@gmail.com",
                "+2348101310757",
                "@Dave",
                LocalDate.now(),
                Role.ROLE_USER.name(),
                Role.ROLE_USER.getAuthorities(),
                true,
                true
        );

        //when
        userService.register(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getPassword()
        );

        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(repository).save(argumentCaptor.capture());
        verify(accountService).createNewAccount(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @Disabled
    void canFindUserByEmail() {
    }

    @Test
    @Disabled
    void canFindUserById() throws UserNotFoundException {
        int userId = 4;
        userService.findUserById(userId);
        verify(repository).findById(userId);
    }
}