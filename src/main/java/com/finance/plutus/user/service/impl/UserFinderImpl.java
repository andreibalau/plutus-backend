package com.finance.plutus.user.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.user.model.Business;
import com.finance.plutus.user.model.User;
import com.finance.plutus.user.repository.BusinessRepository;
import com.finance.plutus.user.repository.UserRepository;
import com.finance.plutus.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class UserFinderImpl implements UserFinder {

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
  public Business getBusiness(UUID userId) {
    return businessRepository
        .findByUserId(userId)
        .orElseThrow(() -> new EntityNotFoundException("business"));
  }
}
