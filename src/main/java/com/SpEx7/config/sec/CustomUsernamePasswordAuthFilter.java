package com.SpEx7.config.sec;

import com.SpEx7.entity.AuthenticationData;
import com.SpEx7.service.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

public class CustomUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationData authenticationData = this.getLoginRequest(request);

        if (!request.getMethod().equals(POST) && (userService.getUserByUsername(authenticationData.getName()) == null)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        UsernamePasswordAuthenticationToken authenticationRequest =
                new UsernamePasswordAuthenticationToken(authenticationData.getName(), authenticationData.getPassword());
        setDetails(request, authenticationRequest);
        return this.getAuthenticationManager().authenticate(authenticationRequest);
    }

    private AuthenticationData getLoginRequest(HttpServletRequest request) {
        AuthenticationData authenticationData = null;
        try (BufferedReader reader = request.getReader()) {
            Gson gson = new Gson();
            authenticationData = gson.fromJson(reader, AuthenticationData.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (authenticationData == null) {
            authenticationData = new AuthenticationData();
        }
        return authenticationData;
    }
}
