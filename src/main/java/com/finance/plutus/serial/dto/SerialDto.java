package com.finance.plutus.serial.dto;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 9/26/2020 */
@Getter
@Setter
public class SerialDto {
  private String id;
  private String name;
  private Integer startNumber;
  private Integer currentNumber;
  private Integer nextNumber;
}
