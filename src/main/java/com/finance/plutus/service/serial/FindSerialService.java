package com.finance.plutus.service.serial;

import com.finance.plutus.model.dto.SerialDto;
import com.finance.plutus.model.entity.Serial;

import java.util.List;

/** Plutus Created by catalin on 9/7/2020 */
public interface FindSerialService {
  boolean existsByName(String name);

  Serial findById(Long id);

  List<SerialDto> findAll();
}
