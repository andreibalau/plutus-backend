package com.finance.plutus.security;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.controller.payload.ErrorResponse;
import com.finance.plutus.exception.TokenInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/** Plutus Created by catalin on 7/1/2020 */
@Component
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

  private final SecurityService securityService;
  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(
      @NotNull HttpServletRequest httpServletRequest,
      @NotNull HttpServletResponse httpServletResponse,
      @NotNull FilterChain filterChain)
      throws ServletException, IOException {
    if (isUnsecuredRequest(httpServletRequest)) {
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      return;
    }
    String token = getTokenFromRequest(httpServletRequest);
    if (token == null) {
      writeResponse(httpServletResponse, "Authorization header is missing!");
    } else {
      authenticateUser(httpServletRequest, httpServletResponse, token, filterChain);
    }
  }

  private boolean isUnsecuredRequest(HttpServletRequest request) {
    AntPathMatcher matcher = new AntPathMatcher();
    return matcher.match("/api/v1/users", request.getRequestURI())
        || matcher.match("/api/v1/users/new", request.getRequestURI())
        || matcher.match("/v2/api-docs", request.getRequestURI())
        || matcher.match("/swagger-ui.html/**", request.getRequestURI())
        || matcher.match("/swagger-resources/**", request.getRequestURI())
        || matcher.match("/webjars/**", request.getRequestURI());
  }

  private void authenticateUser(
      HttpServletRequest request, HttpServletResponse response, String token, FilterChain chain)
      throws IOException, ServletException {
    try {
      securityService.registerInSecurityContext(request, token);
      chain.doFilter(request, response);
    } catch (TokenInvalidException e) {
      writeResponse(response, e.getMessage());
    }
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null
        && authorizationHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
      return authorizationHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
    }
    return null;
  }

  private void writeResponse(HttpServletResponse response, String message) throws IOException {
    response.getWriter().write(objectMapper.writeValueAsString(new ErrorResponse(message)));
    response.addHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}
