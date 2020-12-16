package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.invoice.exception.SerialNameAlreadyExistsException;
import com.finance.plutus.invoice.model.CreateSerialDto;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.repository.SerialRepository;
import com.finance.plutus.invoice.service.SerialCreator;
import com.finance.plutus.invoice.service.SerialFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class SerialCreatorImpl implements SerialCreator {

  private final SerialFinder serialFinder;
  private final SerialRepository serialRepository;

  @Override
  @Transactional
  public UUID create(CreateSerialDto createSerialDto) {
    boolean exists = serialFinder.existsByName(createSerialDto.getName());
    if (exists) {
      throw new SerialNameAlreadyExistsException();
    }
    Serial serial = createSerial(createSerialDto);
    serialRepository.save(serial);
    return serial.getId();
  }

  private Serial createSerial(CreateSerialDto createSerialDto) {
    Serial serial = new Serial();
    serial.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    serial.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    serial.setName(createSerialDto.getName());
    serial.setStartNumber(createSerialDto.getStartNumber());
    serial.setCurrentNumber(serial.getStartNumber());
    serial.setNextNumber(serial.getCurrentNumber());
    return serial;
  }
}
