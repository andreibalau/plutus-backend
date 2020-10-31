package com.finance.plutus.old.service.serial.impl;

import com.finance.plutus.old.model.entity.Serial;
import com.finance.plutus.old.repository.SerialRepository;
import com.finance.plutus.old.service.serial.IncreaseSerialService;
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
    String name = createName(number, serial.getName());
    serial.setCurrentNumber(number);
    serial.setNextNumber(number + 1);
    serialRepository.save(serial);
    return name;
  }

  @Override
  public String getDraftName(Serial serial) {
    int number = serial.getNextNumber();
    return createName(number, serial.getName());
  }

  private String createName(int number, String name) {
    int size = String.valueOf(number).length();
    String formatter = "%s%0" + (size + (4 - size)) + "d";
    return String.format(formatter, name, number);
  }
}
