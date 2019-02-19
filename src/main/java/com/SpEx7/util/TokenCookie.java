package com.SpEx7.util;

import javax.servlet.http.Cookie;

public class TokenCookie {
    private TokenCookie() {
        throw new IllegalStateException("Utility class");
    }

    public static Cookie createTokenCookie(String accessToken, int maxAge) {
        Cookie tokenCookie = new Cookie("x-auth-token", accessToken);
        tokenCookie.setDomain("localhost");
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(maxAge);
        return tokenCookie;
    }
}
