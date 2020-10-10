package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.partner.DeletePartnerService;
import com.finance.plutus.service.partner.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class DeletePartnerServiceImpl implements DeletePartnerService {

  private final FindPartnerService findPartnerService;
  private final PartnerRepository partnerRepository;

  @Override
  @Transactional
  public void delete(UUID id) {
    Partner partner = findPartnerService.findEntityById(id);
    partnerRepository.delete(partner);
  }
}
