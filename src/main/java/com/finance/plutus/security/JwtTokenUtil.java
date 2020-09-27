package com.finance.plutus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/** Plutus Created by catalin on 7/1/2020 */
@Component
public class JwtTokenUtil {

  private final String secret;

  public JwtTokenUtil(@Value("${jwt.secret}") String secret) {
    this.secret = secret;
  }

  public String getUsername(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generate(String username) {
    Map<String, Object> claims = new HashMap<>();
    return generate(claims, username);
  }

  public Boolean isValid(String token, String name) {
    final String username = getUsername(token);
    return (username.equals(name));
  }

  private Claims getAllClaims(String token) {
    return Jwts.parser()
        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
        .parseClaimsJws(token)
        .getBody();
  }

  private String generate(Map<String, Object> claims, String subject) {
    LocalDateTime now = LocalDateTime.now();
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
        .compact();
  }
}
