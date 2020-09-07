package com.finance.plutus.service.serial.impl;

import com.finance.plutus.model.entity.Serial;
import com.finance.plutus.repository.SerialRepository;
import com.finance.plutus.service.serial.IncreaseSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class IncreaseSerialServiceImpl implements IncreaseSerialService {

  private final SerialRepository serialRepository;

  @Override
  @Transactional
  public String getNextName(Serial serial) {
    int number = serial.getNextNumber();
    String name = String.format("%s%04d", serial.getName(), number);
    serial.setCurrentNumber(number);
    serial.setNextNumber(number + 1);
    serialRepository.save(serial);
    return name;
  }
}
