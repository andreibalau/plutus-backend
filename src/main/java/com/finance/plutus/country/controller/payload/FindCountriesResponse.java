package com.finance.plutus.country.controller.payload;

import com.finance.plutus.country.model.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by Catalin on 10/4/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindCountriesResponse {
  private List<CountryDto> countries;
}
