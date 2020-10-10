package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.dto.CreateUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.user.RegisterUserService;
import com.finance.plutus.service.user.UserEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {

  private final UserRepository userRepository;
  private final UserEmailService userEmailService;
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${security.oauth2.resource.user-register-uri}")
  private String userRegisterUri;

  @Override
  @Transactional
  public void register(CreateUserDto createUserDto) {
    userEmailService.checkEmailExistence(createUserDto.getEmail());
    String email = createUserDto.getEmail();
    String password = createUserDto.getPassword();
    User user = createUser(createUserDto);
    userRepository.save(user);
    registerUserInAuthorizationServer(email, password, user.getRole().name());
  }

  private void registerUserInAuthorizationServer(String email, String password, String role) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(userRegisterUri)
            .queryParam("username", email)
            .queryParam("password", password)
            .queryParam("role", role);
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
    restTemplate.postForObject(builder.toUriString(), request, Void.class);
  }

  private User createUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    user.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    user.setEmail(createUserDto.getEmail());
    return user;
  }
}
