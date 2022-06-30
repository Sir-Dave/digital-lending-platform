package com.sirdave.lendingplatform.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignInRequest {
    private String email;
    private String password;
}
