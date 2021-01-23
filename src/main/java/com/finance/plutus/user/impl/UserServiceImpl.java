package com.finance.plutus.user.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.user.Business;
import com.finance.plutus.user.BusinessRepository;
import com.finance.plutus.user.User;
import com.finance.plutus.user.UserRepository;
import com.finance.plutus.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BusinessRepository businessRepository;

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user"));
  }

  @Override
  public User findById(UUID id) {
    return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user"));
  }

  @Override
  public Business getBusiness(String username) {
    return businessRepository
        .findByUserEmail(username)
        .orElseThrow(() -> new EntityNotFoundException("business"));
  }
}
