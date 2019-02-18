package com.SpEx7.config.sec;

import com.SpEx7.entity.PortalUser;
import com.SpEx7.security.UserPrincipal;
import com.SpEx7.service.UserServiceImpl;
import com.SpEx7.som.TokenCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ((UserPrincipal) authentication.getPrincipal()).getUsername();
        System.out.println(username);
        String token = UUID.randomUUID().toString();
        userService.updateToken(username, token);
        response.addCookie(TokenCookie.createTokenCookie(token, 1800));
    }
}
