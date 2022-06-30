package com.sirdave.lendingplatform.exception;

public class PasswordsDoNotMatchException extends Exception {

    public PasswordsDoNotMatchException(String message){
        super(message);
    }
}
