package com.finance.plutus.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 7/1/2020 */
@Getter
@Setter
public class CreateAddressDto {
  @NotBlank private String name;
  @NotBlank private String city;
  @NotNull private Long countyId;
  private String zip;
}
