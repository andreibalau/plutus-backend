package com.finance.plutus.old.service.country;

import com.finance.plutus.country.model.CountryDto;
import com.finance.plutus.country.model.Country;

import java.util.List;

/** Plutus Created by Catalin on 9/27/2020 */
public interface FindCountryService {
  CountryDto findDtoByCode(String code);

  Country findEntityByCode(String code);

  List<CountryDto> findAllDto();
}
