package com.finance.plutus.service.user.impl;

import com.finance.plutus.authorization.KeycloakClient;
import com.finance.plutus.model.dto.CreateUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.user.RegisterUserService;
import com.finance.plutus.service.user.UserEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {

  private final UserRepository userRepository;
  private final UserEmailService userEmailService;
  private final KeycloakClient keycloakClient;

  @Override
  @Transactional
  public void register(CreateUserDto createUserDto) {
    userEmailService.checkEmailExistence(createUserDto.getEmail());
    String email = createUserDto.getEmail();
    String password = createUserDto.getPassword();
    User user = createUser(createUserDto);
    userRepository.save(user);
    keycloakClient.register(email, password);
  }

  private User createUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    user.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    user.setEmail(createUserDto.getEmail());
    return user;
  }
}
