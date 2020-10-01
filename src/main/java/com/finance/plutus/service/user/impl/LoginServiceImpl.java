package com.finance.plutus.service.user.impl;

import java.util.Base64;
import java.util.Map;

import com.finance.plutus.controller.payload.AuthResponse;
import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.service.user.FindUserService;
import com.finance.plutus.service.user.authorization.LoginClient;
import com.finance.plutus.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final FindUserService findUserService;
  private final LoginClient loginClient;

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;

  @Override
  public LoggedUserDto login(String username, String password) {
    User user =
        findUserService
            .findByEmail(username)
            .orElseThrow(() -> new EntityNotFoundException("user"));
    String token = obtainToken(user.getEmail(), password);
    return new LoggedUserDto(user.getEmail(), token);
  }

  private String obtainToken(String username, String password) {
    String credentials = clientId + ":" + clientSecret;
    String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    String authorization = "Basic " + encodedCredentials;
    AuthResponse response =
        loginClient.login(
            authorization,
            Map.of("grant_type", "password", "username", username, "password", password));
    return response.getAccessToken();
  }
}
