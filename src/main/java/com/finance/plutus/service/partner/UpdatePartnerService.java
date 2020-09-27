package com.finance.plutus.service.partner;

import com.finance.plutus.model.dto.UpdatePartnerDto;

/** Plutus Created by Catalin on 8/7/2020 */
public interface UpdatePartnerService {
  void update(String id, UpdatePartnerDto updatePartnerDto);
}
