package com.finance.plutus.old.service.user.impl;

import com.finance.plutus.old.exception.EmailAlreadyExistsException;
import com.finance.plutus.old.repository.UserRepository;
import com.finance.plutus.old.service.user.UserEmailService;
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
