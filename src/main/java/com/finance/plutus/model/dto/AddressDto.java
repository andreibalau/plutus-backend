package com.finance.plutus.model.dto;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class AddressDto {
  private String name;
  private String city;
  private CountyDto county;
  private String zip;
}
