package com.finance.plutus.service.serial;

import com.finance.plutus.model.dto.CreateSerialDto;

import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
public interface CreateSerialService {
  UUID create(CreateSerialDto serial);
}
