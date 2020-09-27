package com.finance.plutus.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.controller.payload.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Plutus Created by catalin on 7/1/2020 */
@Component
@RequiredArgsConstructor
public class PlutusAuthenticationEntryPoint
    implements AuthenticationEntryPoint, AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException {
    doRespond(httpServletResponse);
  }

  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException {
    doRespond(httpServletResponse);
  }

  private void doRespond(HttpServletResponse response) throws IOException {
    response
        .getWriter()
        .write(objectMapper.writeValueAsString(new ErrorResponse("You are not authorized.")));
    response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.FORBIDDEN.value());
  }
}
