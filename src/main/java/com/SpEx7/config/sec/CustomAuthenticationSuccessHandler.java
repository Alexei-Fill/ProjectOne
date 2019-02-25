package com.SpEx7.config.sec;

import com.SpEx7.security.UserPrincipal;
import com.SpEx7.service.UserServiceImpl;
import com.SpEx7.util.TokenCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = ((UserPrincipal) authentication.getPrincipal()).getUsername();
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[50];
        random.nextBytes(bytes);
        String token = bytes.toString();
        userService.updateToken(username, token);
        response.addCookie(TokenCookie.createTokenCookie(token));
    }
}
