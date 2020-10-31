package com.finance.plutus.invoice.service;

import com.finance.plutus.old.model.dto.CreateSerialDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface SerialCreator {
  UUID create(CreateSerialDto serial);
}
