package com.finance.plutus.service.user.impl;

import com.finance.plutus.authorization.KeycloakClient;
import com.finance.plutus.authorization.payload.AuthorizationResponse;
import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.service.user.FindUserService;
import com.finance.plutus.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final FindUserService findUserService;
  private final KeycloakClient keycloakClient;

  @Override
  public LoggedUserDto login(String username, String password) {
    User user = findUserService.findByEmail(username);
    AuthorizationResponse response = keycloakClient.login(username, password);
    return new LoggedUserDto(user.getId(), user.getEmail(), response.getAccessToken());
  }
}
