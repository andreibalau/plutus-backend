package com.finance.plutus.old.service.serial;

import com.finance.plutus.old.model.dto.SerialDto;
import com.finance.plutus.invoice.model.Serial;

import java.util.List;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
public interface FindSerialService {
  boolean existsByName(String name);

  Serial findEntityById(UUID id);

  List<SerialDto> findAllDto();
}
