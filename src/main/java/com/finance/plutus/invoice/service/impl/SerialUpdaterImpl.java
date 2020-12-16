package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.app.exception.PlutusException;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.model.UpdateSerialDto;
import com.finance.plutus.invoice.repository.SerialRepository;
import com.finance.plutus.invoice.service.SerialFinder;
import com.finance.plutus.invoice.service.SerialUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class SerialUpdaterImpl implements SerialUpdater {

  private final SerialRepository serialRepository;
  private final SerialFinder serialFinder;

  @Override
  @Transactional
  public String increment(Serial serial) {
    int number = serial.getNextNumber();
    String name = createName(number, serial.getName());
    serial.setCurrentNumber(number);
    serial.setNextNumber(number + 1);
    serialRepository.save(serial);
    return name;
  }

  @Override
  @Transactional
  public void decrement(Serial serial, int steps) {
    throw new PlutusException("Not Implemented");
  }

  @Override
  @Transactional
  public void update(UUID id, UpdateSerialDto updateSerialDto) {
    Serial serial = serialFinder.findById(id);
    serial.setStartNumber(updateSerialDto.getStartNumber());
    serial.setCurrentNumber(updateSerialDto.getStartNumber());
    serial.setNextNumber(updateSerialDto.getStartNumber());
    serialRepository.save(serial);
  }

  private String createName(int number, String name) {
    int size = String.valueOf(number).length();
    String formatter = "%s%0" + (size + (4 - size)) + "d";
    return String.format(formatter, name, number);
  }
}
