package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.EmailAlreadyExistsException;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.user.UserEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class UserEmailServiceImpl implements UserEmailService {

  private final UserRepository userRepository;

  @Override
  public void checkEmailExistence(String email) {
    boolean exists = userRepository.existsByEmail(email);
    if (exists) {
      throw new EmailAlreadyExistsException();
    }
  }
}
