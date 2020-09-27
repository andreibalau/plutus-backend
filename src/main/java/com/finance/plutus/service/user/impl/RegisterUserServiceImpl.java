package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.dto.CreateUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.user.UserEmailService;
import com.finance.plutus.service.user.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void register(CreateUserDto createUserDto) {
    userEmailService.checkEmailExistence(createUserDto.getEmail());
    User user = createUser(createUserDto);
    userRepository.save(user);
  }

  private User createUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    user.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    user.setEmail(createUserDto.getEmail());
    user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
    return user;
  }
}
