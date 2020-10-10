package com.finance.plutus.service.serial;

import com.finance.plutus.model.dto.SerialDto;
import com.finance.plutus.model.entity.Serial;

import java.util.List;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
public interface FindSerialService {
  boolean existsByName(String name);

  Serial findEntityById(UUID id);

  List<SerialDto> findAllDto();
}
