package com.finance.plutus.user.service.impl;

import com.finance.plutus.user.authorization.KeycloakClient;
import com.finance.plutus.user.authorization.payload.AuthorizationResponse;
import com.finance.plutus.user.model.LoggedUserDto;
import com.finance.plutus.user.model.User;
import com.finance.plutus.user.service.UserAuthenticator;
import com.finance.plutus.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Service
@RequiredArgsConstructor
public class UserAuthenticatorImpl implements UserAuthenticator {

  private final UserFinder userFinder;
  private final KeycloakClient keycloakClient;

  @Override
  public LoggedUserDto login(String username, String password) {
    User user = userFinder.findByEmail(username);
    AuthorizationResponse response = keycloakClient.login(username, password);
    return new LoggedUserDto(user.getId(), user.getEmail(), response.getAccessToken());
  }
}
