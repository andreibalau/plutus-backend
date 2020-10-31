package com.finance.plutus.partner.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.repository.PartnerRepository;
import com.finance.plutus.partner.service.PartnerFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class PartnerFinderImpl implements PartnerFinder {

  private final PartnerRepository partnerRepository;

  @Override
  public Partner findById(UUID id) {
    return partnerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("partner"));
  }

  @Override
  public List<Partner> findAll(PageRequest page) {
    return partnerRepository.findAll(page).stream().collect(Collectors.toList());
  }

  @Override
  public long count() {
    return partnerRepository.count();
  }
}
