package com.finance.plutus.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Setter
public class CreateSerialDto {
  @NotBlank private String name;
  @NotNull private Integer startNumber;
}
