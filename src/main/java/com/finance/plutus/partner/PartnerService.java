package com.finance.plutus.partner;

import com.finance.plutus.partner.dto.CreatePartnerDto;
import com.finance.plutus.partner.dto.UpdatePartnerDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface PartnerService {
  void delete(UUID id);

  UUID create(CreatePartnerDto createPartnerDto);

  Partner findById(UUID id);

  List<Partner> findAll(int page, int size);

  void update(UUID id, UpdatePartnerDto updatePartnerDto);
}
