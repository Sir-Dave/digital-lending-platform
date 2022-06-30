package com.sirdave.lendingplatform.user;

import com.sirdave.lendingplatform.exception.PasswordsDoNotMatchException;
import com.sirdave.lendingplatform.exception.UserExistsException;
import com.sirdave.lendingplatform.exception.UserNotFoundException;
import com.sirdave.lendingplatform.requests.RegisterRequest;
import com.sirdave.lendingplatform.requests.SignInRequest;
import com.sirdave.lendingplatform.security.JwtTokenProvider;
import com.sirdave.lendingplatform.util.HttpResponse;
import com.sirdave.lendingplatform.util.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/user"})
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) throws PasswordsDoNotMatchException, UserExistsException {
        User user = userService.register(
                registerRequest.getFirstname(), registerRequest.getLastname(),
                registerRequest.getEmail(), registerRequest.getPhoneNumber(),
                registerRequest.getPassword(), registerRequest.getConfirmPassword());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody SignInRequest signInRequest) throws UserNotFoundException {
        authenticateUser(signInRequest.getEmail(), signInRequest.getPassword());
        User user = userService.findUserByEmail(signInRequest.getEmail());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        HttpHeaders headers = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) throws UserNotFoundException {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal){
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstants.JWT_TOKEN_HEADER,
                jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticateUser(String email, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}
