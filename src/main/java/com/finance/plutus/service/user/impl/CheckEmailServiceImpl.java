package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.EmailAlreadyExistsException;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.user.CheckEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class CheckEmailServiceImpl implements CheckEmailService {

  private final UserRepository userRepository;
  private final PartnerRepository partnerRepository;

  @Override
  public void checkUserEmailExistence(String email) {
    boolean exists = userRepository.existsByEmail(email);
    if (exists) {
      throw new EmailAlreadyExistsException();
    }
  }

  @Override
  public void checkPartnerEmailExistence(String email) {
    boolean exists = partnerRepository.existsByEmail(email);
    if (exists) {
      throw new EmailAlreadyExistsException();
    }
  }
}
