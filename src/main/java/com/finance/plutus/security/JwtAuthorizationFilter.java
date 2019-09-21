package com.finance.plutus.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends HttpFilter {

    private static final String BEARER = "Bearer ";
    private final JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AUTHORIZATION);
        UsernamePasswordAuthenticationToken authentication = null;

        if (header != null && header.startsWith(BEARER)) {
            authentication = jwtTokenHelper.getAuthentication(header.replace(BEARER, ""));
        }

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}

