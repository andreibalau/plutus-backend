package com.finance.plutus.service.user;

import com.finance.plutus.model.dto.BusinessDto;

import java.util.UUID;

/** Plutus Created by Catalin on 10/13/2020 */
public interface UpdateBusinessService {
  void update(UUID userId, BusinessDto business);
}
