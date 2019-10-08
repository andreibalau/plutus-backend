package com.finance.plutus.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.exception.ErrorDto;
import com.finance.plutus.exception.PlutusException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finance.plutus.exception.PlutusException.FORBIDDEN_OR_UNAUTHORIZED;

public class FailedAuthorizationEntryPoint implements AccessDeniedHandler, AuthenticationEntryPoint {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        doRespond(response);
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        doRespond(response);
    }

    private void doRespond(HttpServletResponse response) throws IOException {
        response
                .getWriter()
                .write(new ObjectMapper()
                        .writeValueAsString(ErrorDto.from(PlutusException.factory(FORBIDDEN_OR_UNAUTHORIZED))));
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}

