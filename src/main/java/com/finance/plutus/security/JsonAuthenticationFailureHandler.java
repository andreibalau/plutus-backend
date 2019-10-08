package com.finance.plutus.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.exception.ErrorDto;
import com.finance.plutus.exception.PlutusException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finance.plutus.exception.PlutusException.FORBIDDEN_OR_UNAUTHORIZED;

public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException authException) throws IOException {
        response
                .getWriter()
                .write(new ObjectMapper()
                        .writeValueAsString(ErrorDto.from(PlutusException.factory(FORBIDDEN_OR_UNAUTHORIZED))));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

}

