package com.finance.plutus.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.finance.plutus.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Plutus
 * Created by catalin on 1/15/2020
 */
@Component
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
	private final JwtTokenUtil jwtTokenUtil;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			FilterChain filterChain
	) throws IOException, ServletException {
		String token = getTokenFromRequest(httpServletRequest);
		authenticateUser(httpServletRequest, token);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private void authenticateUser(HttpServletRequest request, String token) {
		if (token == null) {
			return;
		}
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		boolean isValid = jwtTokenUtil.validateToken(token, userDetails);
		if (!isValid) {
			throw UserException.tokenIsExpired();
		}
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String authzHeaderValue = request.getHeader("Authorization");
		if (authzHeaderValue != null && authzHeaderValue.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
			return authzHeaderValue.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		}
		return null;
	}

}
