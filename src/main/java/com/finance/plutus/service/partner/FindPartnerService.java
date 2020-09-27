package com.finance.plutus.service.partner;

import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.entity.Partner;

import java.util.List;

/** Plutus Created by catalin on 7/2/2020 */
public interface FindPartnerService {
  PartnerDto findDtoById(String id);

  Partner findEntityById(String id);

  List<PartnerDto> findAllDtoByPage(int page, int size);

  long countAll();
}
