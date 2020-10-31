package com.finance.plutus.old.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.old.configuration.Api;
import com.finance.plutus.old.controller.payload.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Plutus Created by Catalin on 9/29/2020 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
      throws IOException, ServletException {
    response
        .getWriter()
        .write(new ObjectMapper().writeValueAsString(new ErrorResponse("Access denied.")));
    response.addHeader(HttpHeaders.CONTENT_TYPE, Api.APPLICATION_VND_PLUTUS_FINANCE_JSON);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}
