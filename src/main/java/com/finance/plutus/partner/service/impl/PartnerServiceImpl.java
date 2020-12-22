package com.finance.plutus.partner.service.impl;

import com.finance.plutus.partner.model.CreatePartnerDto;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.model.PartnerDto;
import com.finance.plutus.partner.model.UpdatePartnerDto;
import com.finance.plutus.partner.service.PartnerCleaner;
import com.finance.plutus.partner.service.PartnerCreator;
import com.finance.plutus.partner.service.PartnerFinder;
import com.finance.plutus.partner.service.PartnerService;
import com.finance.plutus.partner.service.PartnerUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

  private final PartnerCreator partnerCreator;
  private final PartnerUpdater partnerUpdater;
  private final PartnerCleaner partnerCleaner;
  private final PartnerFinder partnerFinder;

  @Override
  public UUID create(CreatePartnerDto partner) {
    return partnerCreator.create(partner);
  }

  @Override
  public void update(UUID id, UpdatePartnerDto partner) {
    partnerUpdater.update(id, partner);
  }

  @Override
  public void delete(UUID id) {
    partnerCleaner.delete(id);
  }

  @Override
  public PartnerDto findById(UUID id) {
    Partner partner = partnerFinder.findById(id);
    return PartnerDto.mapFromEntity(partner);
  }

  @Override
  public List<PartnerDto> findAll(int page, int size) {
    return partnerFinder.findAll(PageRequest.of(page, size)).stream()
        .map(PartnerDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long count() {
    return partnerFinder.count();
  }
}
