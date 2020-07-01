package com.finance.plutus.service.impl;

import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.CheckEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class CheckEmailServiceImpl implements CheckEmailService {

  private final UserRepository userRepository;

  @Override
  public boolean exists(String email) {
    return userRepository.existsByEmail(email);
  }
}
