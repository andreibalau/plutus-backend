package com.finance.plutus.service.partner;

import com.finance.plutus.model.dto.UpdatePartnerDto;

import java.util.UUID;

/** Plutus Created by Catalin on 8/7/2020 */
public interface UpdatePartnerService {
  void update(UUID id, UpdatePartnerDto updatePartnerDto);
}
