package com.finance.plutus.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@RequiredArgsConstructor
public class JwtTokenHelper {

    private static final int TEN_DAYS_MILLIS = 864000000;

    private final String jwtSecret;
    private final UserDetailsService userDetailsService;

    public String authenticate(Authentication authentication) {
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .setSubject(((UserPrincipal) authentication.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TEN_DAYS_MILLIS))
                .claim("role", roles)
                .compact();

    }

    public UsernamePasswordAuthenticationToken getAuthentication(String request) {
        Jws<Claims> parsedToken;

        byte[] signingKey = jwtSecret.getBytes();
        try {
            parsedToken = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(request);
        } catch (JwtException e) {
            return null;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(parsedToken.getBody().getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
