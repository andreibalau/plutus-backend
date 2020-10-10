package com.finance.plutus.service.partner;

import com.finance.plutus.model.dto.CreatePartnerDto;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface CreatePartnerService {
  UUID create(CreatePartnerDto createPartnerDto);
}
