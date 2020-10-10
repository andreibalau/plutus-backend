package com.finance.plutus.service.user.impl;

import com.finance.plutus.controller.payload.AuthResponse;
import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.exception.PlutusException;
import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.service.user.FindUserService;
import com.finance.plutus.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Optional;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final FindUserService findUserService;
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;

  @Value("${security.oauth2.resource.user-login-uri}")
  private String userLoginUri;

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
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth(encodedCredentials);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("username", username);
    map.add("password", password);
    map.add("grant_type", "password");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    ResponseEntity<AuthResponse> response =
        restTemplate.postForEntity(userLoginUri, request, AuthResponse.class);
    return Optional.ofNullable(response.getBody())
        .map(AuthResponse::getAccessToken)
        .orElseThrow(() -> new PlutusException("Login failed"));
  }
}
