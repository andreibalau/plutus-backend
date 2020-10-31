package com.finance.plutus.partner.service.impl;

import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.repository.PartnerRepository;
import com.finance.plutus.partner.service.PartnerCleaner;
import com.finance.plutus.partner.service.PartnerFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class PartnerCleanerImpl implements PartnerCleaner {

  private final PartnerFinder partnerFinder;
  private final PartnerRepository partnerRepository;

  @Override
  @Transactional
  public void delete(UUID id) {
    Partner partner = partnerFinder.findById(id);
    partnerRepository.delete(partner);
  }
}
