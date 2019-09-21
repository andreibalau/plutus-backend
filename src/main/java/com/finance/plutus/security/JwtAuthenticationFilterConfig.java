package com.finance.plutus.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
public class JwtAuthenticationFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AntPathRequestMatcher antPathRequestMatcher;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenHelper jwtTokenHelper;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthenticationFilter customFilter = new JwtAuthenticationFilter(antPathRequestMatcher,
                authenticationManager, jwtTokenHelper, authenticationFailureHandler);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

