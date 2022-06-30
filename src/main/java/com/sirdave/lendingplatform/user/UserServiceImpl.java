package com.sirdave.lendingplatform.user;

import com.sirdave.lendingplatform.exception.PasswordsDoNotMatchException;
import com.sirdave.lendingplatform.exception.UserExistsException;
import com.sirdave.lendingplatform.exception.UserNotFoundException;
import com.sirdave.lendingplatform.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@Qualifier("userDetailsService")
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with username " + username  + " not found"));

        userRepository.save(user);
        return new UserPrincipal(user);
    }

    @Override
    public User register(String firstname, String lastname,
                         String email, String phoneNumber,
                         String password, String confirmPassword) throws PasswordsDoNotMatchException, UserExistsException {

        validateUser(email);
        doPasswordsMatch(password, confirmPassword);

        String encodedPassword = encodePassword(password);

        User user = new User(firstname, lastname, email,
                phoneNumber, encodedPassword, LocalDate.now(),
                Role.ROLE_USER.name(), Role.ROLE_USER.getAuthorities(),
                true, true, false);

        return userRepository.save(user);
    }


    @Override
    public User findUserById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist"));
    }


    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " does not exist"));
    }

    private void doPasswordsMatch(String p1, String p2) throws PasswordsDoNotMatchException {
        if (!p1.equals(p2)){
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }
    }

    private void validateUser(String email) throws UserExistsException {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail.isPresent()){
            throw new UserExistsException("A user with this email already exists");
        }
    }

    private String encodePassword(String password){
        return bCryptPasswordEncoder().encode(password);
    }

}
