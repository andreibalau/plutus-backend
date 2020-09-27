package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.EmailAlreadyExistsException;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.partner.PartnerEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by Catalin on 9/27/2020 */
@Service
@RequiredArgsConstructor
public class PartnerEmailServiceImpl implements PartnerEmailService {

  private final PartnerRepository partnerRepository;

  @Override
  public void checkEmailExistence(String email) {
    boolean exists = partnerRepository.existsByEmail(email);
    if (exists) {
      throw new EmailAlreadyExistsException();
    }
  }
}
