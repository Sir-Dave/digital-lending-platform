package com.sirdave.lendingplatform.util;

import static com.sirdave.lendingplatform.util.Authority.USER_AUTHORITIES;

public enum Role {
    ROLE_USER(USER_AUTHORITIES);

    private final String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
