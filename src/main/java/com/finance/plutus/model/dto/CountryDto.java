package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Country;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 9/27/2020 */
@Getter
@Setter
public class CountryDto {
  private String code;
  private String name;

  public static CountryDto mapFromEntity(Country country) {
    CountryDto countryDto = new CountryDto();
    countryDto.setCode(country.getCode());
    countryDto.setName(country.getName());
    return countryDto;
  }
}
