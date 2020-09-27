package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Serial;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 9/26/2020 */
@Getter
@Setter
public class SerialDto {

  private Long id;
  private String name;
  private Integer startNumber;
  private Integer currentNumber;
  private Integer nextNumber;

  public static SerialDto fromEntity(Serial serial) {
    SerialDto serialDto = new SerialDto();
    serialDto.setId(serial.getId());
    serialDto.setName(serial.getName());
    serialDto.setCurrentNumber(serial.getCurrentNumber());
    serialDto.setStartNumber(serial.getStartNumber());
    serialDto.setNextNumber(serial.getNextNumber());
    return serialDto;
  }
}
