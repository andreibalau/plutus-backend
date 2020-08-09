package com.finance.plutus.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class UpdateAddressDto {
  @NotBlank private String name;
  @NotBlank private String city;
  @NotNull private Long countyId;
  private String zip;
}
