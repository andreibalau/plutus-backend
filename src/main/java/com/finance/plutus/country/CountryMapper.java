package com.finance.plutus.country;

import org.springframework.stereotype.Component;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
public class CountryMapper {
  public CountryDto mapToDto(Country country) {
    CountryDto countryDto = new CountryDto();
    countryDto.setCode(country.getCode());
    countryDto.setName(country.getName());
    return countryDto;
  }
}
