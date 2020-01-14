package com.finance.plutus.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.model.user.dto.AuthenticationDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String AUTHENTICATION_TOKEN = "Bearer %s";
    private final JwtTokenHelper jwtTokenHelper;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(
            RequestMatcher requiresAuthenticationRequestMatcher,
            AuthenticationManager authenticationManager,
            JwtTokenHelper jwtTokenHelper,
            AuthenticationFailureHandler customAuthenticationEntryPoint,
            ObjectMapper objectMapper) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.objectMapper = objectMapper;
        setRequiresAuthenticationRequestMatcher(requiresAuthenticationRequestMatcher);
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(customAuthenticationEntryPoint);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthenticationRequestWrapper authenticationRequestWrapper = new AuthenticationRequestWrapper(request);
        return super.attemptAuthentication(authenticationRequestWrapper, response);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return getAuthenticationDto(new HttpServletRequestWrapper(request)).getPassword();
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return getAuthenticationDto(request).getUsername();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.addHeader(AUTHORIZATION, String.format(AUTHENTICATION_TOKEN, jwtTokenHelper.authenticate(authentication)));
        filterChain.doFilter(request, response);
    }

    private AuthenticationDto getAuthenticationDto(HttpServletRequest request) {
        try {
            return objectMapper
                    .readValue(request
                            .getReader()
                            .lines()
                            .collect(Collectors.joining(System.lineSeparator())),
                            AuthenticationDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class AuthenticationRequestWrapper
            extends HttpServletRequestWrapper {

        private final String payload;

        private AuthenticationRequestWrapper(HttpServletRequest request)
                throws AuthenticationException {

            super(request);
            try {
                payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new StringReader(payload));
        }
    }
}
