package com.sirdave.lendingplatform.user;

import com.sirdave.lendingplatform.exception.PasswordsDoNotMatchException;
import com.sirdave.lendingplatform.exception.UserExistsException;
import com.sirdave.lendingplatform.exception.UserNotFoundException;

public interface UserService {

    User register(String firstname, String lastname,
                  String email, String phoneNumber,
                  String password, String confirmPassword) throws PasswordsDoNotMatchException, UserExistsException;

    User findUserByEmail(String email) throws UserNotFoundException;

    User findUserById(int id) throws UserNotFoundException;

}
