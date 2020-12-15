package com.finance.plutus.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.app.configuration.Api;
import com.finance.plutus.app.payload.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Plutus Created by Catalin on 9/29/2020 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
      throws IOException, ServletException {
    e.printStackTrace();
    response
        .getWriter()
        .write(new ObjectMapper().writeValueAsString(new ErrorResponse("Authentication failed.")));
    response.addHeader(HttpHeaders.CONTENT_TYPE, Api.APPLICATION_VND_PLUTUS_FINANCE_JSON);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}
