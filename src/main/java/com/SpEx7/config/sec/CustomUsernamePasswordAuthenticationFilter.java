package com.SpEx7.config.sec;

import com.SpEx7.entity.LoginRequest;
import com.SpEx7.service.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    private UserServiceImpl userService;

    public CustomUsernamePasswordAuthenticationFilter(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LoginRequest loginRequest = this.getLoginRequest(request);

        if (!request.getMethod().equals("POST") && (userService.getUserByUsername(loginRequest.getName()) == null)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword());
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private LoginRequest getLoginRequest(HttpServletRequest request) {
        LoginRequest loginRequest = null;
        try (BufferedReader reader = request.getReader()) {
            Gson gson = new Gson();
            loginRequest = gson.fromJson(reader, LoginRequest.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (loginRequest == null) {
            loginRequest = new LoginRequest();
        }

        return loginRequest;
    }
}
