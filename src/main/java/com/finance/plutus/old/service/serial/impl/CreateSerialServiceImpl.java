package com.finance.plutus.old.service.serial.impl;

import com.finance.plutus.old.exception.SerialNameAlreadyExistsException;
import com.finance.plutus.old.model.dto.CreateSerialDto;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.repository.SerialRepository;
import com.finance.plutus.old.service.serial.CreateSerialService;
import com.finance.plutus.old.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class CreateSerialServiceImpl implements CreateSerialService {

  private final SerialRepository serialRepository;
  private final FindSerialService findSerialService;

  @Override
  @Transactional
  public UUID create(CreateSerialDto createSerialDto) {
    boolean exists = findSerialService.existsByName(createSerialDto.getName());
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
    serial.setCurrentNumber(serial.getStartNumber() - 1);
    serial.setNextNumber(serial.getCurrentNumber());
    return serial;
  }
}
