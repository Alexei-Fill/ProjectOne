package com.SpEx7.config;

import com.SpEx7.config.sec.*;
import com.SpEx7.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity

public class WebSecurityConfigRest extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;
//    @Autowired
//    private AuthenticationUserDetailsService authenticationUserDetailsService;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

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
        TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(userServiceImpl);
        http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
        http.addFilterBefore(customUsernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/rest/news").permitAll()

                .antMatchers("/**").permitAll()
                .and()
                .logout();
//
//        http.authorizeRequests().anyRequest().permitAll()
//                .and().authorizeRequests().antMatchers("/showLogin**").anonymous()
//                .and().formLogin().defaultSuccessUrl("/newsList").failureUrl("/showLogin?error=true")
//                .loginPage("/showLogin").loginProcessingUrl("/login").permitAll().usernameParameter("login").passwordParameter("password")
//                .and().logout().logoutSuccessUrl("/showLogin").and().authorizeRequests().antMatchers("/newsList").permitAll()
//                .and().exceptionHandling().accessDeniedPage("/forbidden").and().csrf().disable();
//        CharacterEncodingFilter encodingFilter =   new CharacterEncodingFilter("UTF-8", true);
//        http.addFilterBefore(encodingFilter, CsrfFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userServiceImpl);
        return authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
            throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter(userServiceImpl);
        customUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        customUsernamePasswordAuthenticationFilter
                .setAuthenticationManager(authenticationManagerBean());
        return customUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
         configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

