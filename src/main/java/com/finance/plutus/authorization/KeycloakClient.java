package com.finance.plutus.authorization;

import com.finance.plutus.authorization.payload.AuthorizationResponse;
import com.finance.plutus.authorization.payload.RegisterRequest;
import com.finance.plutus.authorization.payload.UserCredentials;
import com.finance.plutus.configuration.KeycloakConfiguration;
import com.finance.plutus.exception.PlutusException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

/** Plutus Created by Catalin on 10/17/2020 */
@Service
@RequiredArgsConstructor
public class KeycloakClient {

  private final KeycloakConfiguration keycloakConfiguration;
  private final RestTemplate restTemplate = new RestTemplate();

  public void register(String email, String password) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    AuthorizationResponse loginResponse = loginAsAdmin();
    headers.setBearerAuth(loginResponse.getAccessToken());
    RegisterRequest registerRequest = new RegisterRequest();
    registerRequest.setUsername(email);
    registerRequest.setEmail(email);
    registerRequest.setCredentials(List.of(new UserCredentials(password)));
    HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);
    restTemplate.postForObject(keycloakConfiguration.getRegisterUri(), request, Void.class);
  }

  public AuthorizationResponse login(String username, String password) {
    String credentials =
        keycloakConfiguration.getClientId() + ":" + keycloakConfiguration.getClientSecret();
    String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth(encodedCredentials);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("username", username);
    map.add("password", password);
    map.add("grant_type", "password");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    ResponseEntity<AuthorizationResponse> response =
        restTemplate.postForEntity(
            keycloakConfiguration.getLoginUri(), request, AuthorizationResponse.class);
    return Optional.ofNullable(response.getBody())
        .orElseThrow(() -> new PlutusException("Login failed"));
  }

  private AuthorizationResponse loginAsAdmin() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("client_id", keycloakConfiguration.getAdminClientId());
    map.add("client_secret", keycloakConfiguration.getAdminClientSecret());
    map.add("grant_type", "client_credentials");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    ResponseEntity<AuthorizationResponse> response =
        restTemplate.postForEntity(
            keycloakConfiguration.getLoginUri(), request, AuthorizationResponse.class);
    return Optional.ofNullable(response.getBody())
        .orElseThrow(() -> new PlutusException("Login failed"));
  }
}
