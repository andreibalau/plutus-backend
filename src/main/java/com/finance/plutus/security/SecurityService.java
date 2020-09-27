package com.finance.plutus.security;

import com.finance.plutus.exception.TokenInvalidException;
import com.finance.plutus.service.user.FindUserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class SecurityService {

  private final JwtTokenUtil jwtTokenUtil;
  private final FindUserService findUserService;

  public void registerInSecurityContext(HttpServletRequest request, String token)
      throws TokenInvalidException {
    UserDetails userDetails = findUserDetailsFromToken(token);
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private UserDetails findUserDetailsFromToken(String token) throws TokenInvalidException {
    String username = getUsername(token);
    checkValidity(token, username);
    return findUserService
        .findByEmail(username)
        .map(
            u ->
                new User(
                    u.getEmail(),
                    u.getPassword(),
                    List.of(new SimpleGrantedAuthority(u.getRole().name()))))
        .orElseThrow(TokenInvalidException::new);
  }

  private String getUsername(String token) throws TokenInvalidException {
    try {
      return jwtTokenUtil.getUsername(token);
    } catch (ExpiredJwtException e) {
      throw new TokenInvalidException();
    }
  }

  private void checkValidity(String token, String username) throws TokenInvalidException {
    boolean isValid = jwtTokenUtil.isValid(token, username);
    if (!isValid) {
      throw new TokenInvalidException();
    }
  }
}
