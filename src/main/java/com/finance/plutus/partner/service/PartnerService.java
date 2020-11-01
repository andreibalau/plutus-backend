package com.finance.plutus.partner.service;

import com.finance.plutus.partner.model.CreatePartnerDto;
import com.finance.plutus.partner.model.PartnerDto;
import com.finance.plutus.partner.model.UpdatePartnerDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface PartnerService {
  UUID create(CreatePartnerDto partner);

  void update(UUID id, UpdatePartnerDto partner);

  void delete(UUID id);

  PartnerDto findById(UUID id);

  List<PartnerDto> findAll(int page, int size);

  long count();
}
