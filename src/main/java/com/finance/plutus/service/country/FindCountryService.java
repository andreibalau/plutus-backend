package com.finance.plutus.service.country;

import com.finance.plutus.model.dto.CountryDto;
import com.finance.plutus.model.entity.Country;

import java.util.List;

/** Plutus Created by Catalin on 9/27/2020 */
public interface FindCountryService {
  CountryDto findDtoByCode(String code);

  Country findEntityByCode(String code);

  List<CountryDto> findAllDto();
}
