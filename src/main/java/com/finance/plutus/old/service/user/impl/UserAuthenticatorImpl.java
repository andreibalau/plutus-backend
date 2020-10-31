package com.finance.plutus.old.service.user.impl;

import com.finance.plutus.old.authorization.KeycloakClient;
import com.finance.plutus.old.authorization.payload.AuthorizationResponse;
import com.finance.plutus.old.model.dto.LoggedUserDto;
import com.finance.plutus.old.model.entity.User;
import com.finance.plutus.old.service.user.FindUserService;
import com.finance.plutus.old.service.user.UserAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Service
@RequiredArgsConstructor
public class UserAuthenticatorImpl implements UserAuthenticator {

  private final FindUserService findUserService;
  private final KeycloakClient keycloakClient;

  @Override
  public LoggedUserDto login(String username, String password) {
    User user = findUserService.findByEmail(username);
    AuthorizationResponse response = keycloakClient.login(username, password);
    return new LoggedUserDto(user.getId(), user.getEmail(), response.getAccessToken());
  }
}
