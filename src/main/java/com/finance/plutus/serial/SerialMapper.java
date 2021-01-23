package com.finance.plutus.serial;

import com.finance.plutus.serial.dto.SerialDto;
import org.springframework.stereotype.Component;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
public class SerialMapper {

  public SerialDto mapToDto(Serial serial) {
    SerialDto serialDto = new SerialDto();
    serialDto.setId(serial.getId().toString());
    serialDto.setName(serial.getName());
    serialDto.setCurrentNumber(serial.getCurrentNumber());
    serialDto.setStartNumber(serial.getStartNumber());
    serialDto.setNextNumber(serial.getNextNumber());
    return serialDto;
  }
}
