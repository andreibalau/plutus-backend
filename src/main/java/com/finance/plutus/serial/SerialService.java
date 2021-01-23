package com.finance.plutus.serial;

import com.finance.plutus.serial.dto.UpdateSerialDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface SerialService {

  String increment(Serial serial);

  void decrement(Serial serial, int steps);

  void update(UUID id, UpdateSerialDto updateSerialDto);

  boolean existsByName(String name);

  Serial findById(UUID id);

  List<Serial> findAll();
}
