package com.finance.plutus.service.user;

import com.finance.plutus.model.dto.BusinessDto;
import com.finance.plutus.model.entity.Business;

/** Plutus Created by Catalin on 10/13/2020 */
public interface FindBusinessService {
  BusinessDto getDto();

  Business getEntity();
}
