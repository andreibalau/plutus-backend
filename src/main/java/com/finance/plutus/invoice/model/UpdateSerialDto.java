package com.finance.plutus.invoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 12/16/2020 */
@Getter
@Setter
public class UpdateSerialDto {
  @NotNull private Integer startNumber;
}
