package com.finance.plutus.old.service.user;

import com.finance.plutus.old.model.dto.BusinessDto;
import com.finance.plutus.old.model.entity.Business;

/** Plutus Created by Catalin on 10/13/2020 */
public interface FindBusinessService {
  BusinessDto getDto();

  Business getEntity();
}
