package com.finance.plutus.old.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Plutus Created by Catalin on 10/18/2020 */
public class KeycloakJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  @Override
  public AbstractAuthenticationToken convert(Jwt source) {
    List<SimpleGrantedAuthority> authorities =
        Optional.ofNullable(source.getClaimAsString("role"))
            .map(role -> Collections.singletonList(new SimpleGrantedAuthority(role)))
            .orElse(Collections.emptyList());
    return new JwtAuthenticationToken(source, authorities);
  }
}
