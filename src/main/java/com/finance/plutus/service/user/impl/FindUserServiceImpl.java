package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.UserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class FindUserServiceImpl implements FindUserService {

  private final UserRepository userRepository;

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserDto getProfile() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user"));
    return UserDto.mapFromEntity(user);
  }
}
