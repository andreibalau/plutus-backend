package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.invoice.model.CreateSerialDto;
import com.finance.plutus.invoice.model.SerialDto;
import com.finance.plutus.invoice.service.SerialCreator;
import com.finance.plutus.invoice.service.SerialFinder;
import com.finance.plutus.invoice.service.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class SerialServiceImpl implements SerialService {

  private final SerialFinder serialFinder;
  private final SerialCreator serialCreator;

  @Override
  public UUID create(CreateSerialDto serial) {
    return serialCreator.create(serial);
  }

  @Override
  public List<SerialDto> findAll() {
    return serialFinder.findAll().stream()
        .map(SerialDto::mapFromEntity)
        .collect(Collectors.toList());
  }
}
