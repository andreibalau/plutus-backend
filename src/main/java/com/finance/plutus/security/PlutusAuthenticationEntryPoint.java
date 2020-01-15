package com.finance.plutus.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.plutus.exception.ErrorResponse;
import com.finance.plutus.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Plutus
 * Created by catalin on 1/15/2020
 */
@Component
@RequiredArgsConstructor
public class PlutusAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			AuthenticationException e
	) throws IOException {
		doRespond(httpServletResponse, UserException.userIsUnauthorized());
	}

	@Override
	public void handle(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			AccessDeniedException e
	) throws IOException {
		doRespond(httpServletResponse, UserException.userIsForbidden());
	}

	private void doRespond(HttpServletResponse response, UserException e) throws IOException {
		response
				.getWriter()
				.write(objectMapper.writeValueAsString(ErrorResponse.from(e)));
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(e.getHttpStatus().value());
	}

}
