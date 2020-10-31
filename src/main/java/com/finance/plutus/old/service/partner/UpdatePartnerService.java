package com.finance.plutus.old.service.partner;

import com.finance.plutus.old.model.dto.UpdatePartnerDto;

import java.util.UUID;

/** Plutus Created by Catalin on 8/7/2020 */
public interface UpdatePartnerService {
  void update(UUID id, UpdatePartnerDto updatePartnerDto);
}
