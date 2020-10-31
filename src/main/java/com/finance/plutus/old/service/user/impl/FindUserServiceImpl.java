package com.finance.plutus.old.service.user.impl;

import com.finance.plutus.old.exception.EntityNotFoundException;
import com.finance.plutus.old.model.entity.User;
import com.finance.plutus.old.repository.UserRepository;
import com.finance.plutus.old.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class FindUserServiceImpl implements FindUserService {

  private final UserRepository userRepository;

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user"));
  }

  @Override
  public User findById(UUID id) {
    return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user"));
  }
}
