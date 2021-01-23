package com.finance.plutus.serial.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.app.exception.PlutusException;
import com.finance.plutus.serial.Serial;
import com.finance.plutus.serial.SerialRepository;
import com.finance.plutus.serial.SerialService;
import com.finance.plutus.serial.dto.UpdateSerialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class SerialServiceImpl implements SerialService {

  private final SerialRepository serialRepository;

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
    Serial serial = findById(id);
    serial.setStartNumber(updateSerialDto.getStartNumber());
    serial.setCurrentNumber(updateSerialDto.getStartNumber());
    serial.setNextNumber(updateSerialDto.getStartNumber());
    serialRepository.save(serial);
  }

  @Override
  public boolean existsByName(String name) {
    return serialRepository.existsByName(name);
  }

  @Override
  public Serial findById(UUID id) {
    return serialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("serial"));
  }

  @Override
  public List<Serial> findAll() {
    return serialRepository.findAll();
  }

  private String createName(int number, String name) {
    int size = String.valueOf(number).length();
    String formatter = "%s%0" + (size + (4 - size)) + "d";
    return String.format(formatter, name, number);
  }
}
