package com.finance.plutus.user.service.impl;

import com.finance.plutus.user.authorization.KeycloakClient;
import com.finance.plutus.user.model.CreateUserDto;
import com.finance.plutus.user.model.User;
import com.finance.plutus.user.repository.UserRepository;
import com.finance.plutus.user.service.UserCreator;
import com.finance.plutus.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class UserCreatorImpl implements UserCreator {

  private final UserFinder userFinder;
  private final KeycloakClient keycloakClient;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public void register(CreateUserDto createUserDto) {
    userFinder.validateEmailExistence(createUserDto.getEmail());
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
