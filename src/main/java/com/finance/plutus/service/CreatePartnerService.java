package com.finance.plutus.service;

import com.finance.plutus.model.dto.CreateBusinessDto;
import com.finance.plutus.model.dto.CreatePartnerDto;

/** Plutus Created by catalin on 7/2/2020 */
public interface CreatePartnerService {
  Long create(CreatePartnerDto createPartnerDto, CreateBusinessDto createBusinessDto);
}
