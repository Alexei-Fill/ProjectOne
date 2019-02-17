package com.SpEx7.config.sec;

import com.SpEx7.entity.PortalUser;
import com.SpEx7.service.UserServiceImpl;
import com.SpEx7.som.TokenCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private UserServiceImpl userService;

    public TokenAuthenticationFilter(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        Cookie cookie = WebUtils.getCookie(request, "auth-token");
        String accessToken = null;
        if (cookie != null) {
            accessToken = cookie.getValue();
            response.addCookie(TokenCookie.createTokenCookie(accessToken, 1800));
        }

        if (accessToken != null ) {
            System.out.println(accessToken);
           PortalUser userEntity = userService.getUserByToken(accessToken);
            System.out.println(accessToken);

            final User user = new User(
                    userEntity.getLogin(),
                    userEntity.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    Collections.emptyList());
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            System.out.println("auth t " + authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
