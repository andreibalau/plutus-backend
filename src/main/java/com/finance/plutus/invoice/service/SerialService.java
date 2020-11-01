package com.finance.plutus.invoice.service;

import com.finance.plutus.invoice.model.CreateSerialDto;
import com.finance.plutus.invoice.model.SerialDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface SerialService {

  UUID create(CreateSerialDto serial);

  List<SerialDto> findAll();
}
