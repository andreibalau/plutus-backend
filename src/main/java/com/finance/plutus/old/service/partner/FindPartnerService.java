package com.finance.plutus.old.service.partner;

import com.finance.plutus.old.model.dto.PartnerDto;
import com.finance.plutus.old.model.entity.Partner;

import java.util.List;
import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface FindPartnerService {
  PartnerDto findDtoById(UUID id);

  Partner findEntityById(UUID id);

  List<PartnerDto> findAllDtoByPage(int page, int size);

  long countAll();
}
