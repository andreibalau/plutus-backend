package com.finance.plutus.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtTokenFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenHelper jwtTokenHelper;

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthorizationFilter customFilter = new JwtAuthorizationFilter(jwtTokenHelper);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

