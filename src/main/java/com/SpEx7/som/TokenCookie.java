package com.SpEx7.som;

import javax.servlet.http.Cookie;

public class TokenCookie {
    private TokenCookie() {
        throw new IllegalStateException("Utility class");
    }

    public static Cookie createTokenCookie(String accessToken, int maxAge) {
        Cookie tokenCookie = new Cookie("auth-token", accessToken);
        tokenCookie.setDomain("localhost");
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(maxAge);
        return tokenCookie;
    }
}
