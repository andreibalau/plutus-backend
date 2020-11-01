package com.finance.plutus.user.service;

import com.finance.plutus.user.model.BusinessDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface BusinessUpdater {
  void update(UUID userId, BusinessDto business);
}
