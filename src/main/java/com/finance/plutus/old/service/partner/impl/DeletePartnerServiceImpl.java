package com.finance.plutus.old.service.partner.impl;

import com.finance.plutus.old.model.entity.Partner;
import com.finance.plutus.old.repository.PartnerRepository;
import com.finance.plutus.old.service.partner.DeletePartnerService;
import com.finance.plutus.old.service.partner.FindPartnerService;
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
