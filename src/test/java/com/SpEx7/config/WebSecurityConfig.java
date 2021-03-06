package com.SpEx7.config;

import com.SpEx7.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       authenticationProvider().setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**");
        http.authorizeRequests().anyRequest().permitAll()
                .and().authorizeRequests().antMatchers("/showLogin**").anonymous()
                .and().formLogin().defaultSuccessUrl("/newsList").failureUrl("/showLogin?error=true")
                .loginPage("/showLogin").loginProcessingUrl("/login").permitAll().usernameParameter("login").passwordParameter("password")
                .and().logout().logoutSuccessUrl("/showLogin").and().authorizeRequests().antMatchers("/newsList").permitAll()
                .and().exceptionHandling().accessDeniedPage("/forbidden").and().csrf().disable();
        CharacterEncodingFilter encodingFilter =   new CharacterEncodingFilter(UTF_8.toString(), true);
        http.addFilterBefore(encodingFilter, CsrfFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userServiceImpl);
        return authenticationProvider;
    }
}

