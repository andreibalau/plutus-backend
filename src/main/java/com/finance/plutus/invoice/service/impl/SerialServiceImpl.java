package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.invoice.model.CreateSerialDto;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.model.SerialDto;
import com.finance.plutus.invoice.model.UpdateSerialDto;
import com.finance.plutus.invoice.service.SerialCreator;
import com.finance.plutus.invoice.service.SerialFinder;
import com.finance.plutus.invoice.service.SerialService;
import com.finance.plutus.invoice.service.SerialUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class SerialServiceImpl implements SerialService {

  private final SerialFinder serialFinder;
  private final SerialCreator serialCreator;
  private final SerialUpdater serialUpdater;

  @Override
  public UUID create(CreateSerialDto serial) {
    return serialCreator.create(serial);
  }

  @Override
  public SerialDto findById(UUID id) {
    Serial serial = serialFinder.findById(id);
    return SerialDto.mapFromEntity(serial);
  }

  @Override
  public void update(UUID id, UpdateSerialDto serial) {
    serialUpdater.update(id, serial);
  }
}
