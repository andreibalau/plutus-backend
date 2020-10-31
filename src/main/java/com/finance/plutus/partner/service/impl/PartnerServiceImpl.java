package com.finance.plutus.partner.service.impl;

import com.finance.plutus.old.model.dto.CreatePartnerDto;
import com.finance.plutus.old.model.dto.PartnerDto;
import com.finance.plutus.old.model.dto.UpdatePartnerDto;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.service.*;
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
