package com.finance.plutus.partner.service;

import com.finance.plutus.old.model.dto.UpdatePartnerDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface PartnerUpdater {
  void update(UUID id, UpdatePartnerDto partner);
}
