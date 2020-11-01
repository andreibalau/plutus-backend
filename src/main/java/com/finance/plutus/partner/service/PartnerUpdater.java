package com.finance.plutus.partner.service;

import com.finance.plutus.partner.model.UpdatePartnerDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface PartnerUpdater {
  void update(UUID id, UpdatePartnerDto partner);
}
