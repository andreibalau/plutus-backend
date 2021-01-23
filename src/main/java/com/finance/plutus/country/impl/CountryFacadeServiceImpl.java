package com.finance.plutus.country.impl;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.country.CountryDto;
import com.finance.plutus.country.CountryFacadeService;
import com.finance.plutus.country.CountryMapper;
import com.finance.plutus.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class CountryFacadeServiceImpl implements CountryFacadeService {

  private final CountryService countryService;
  private final CountryMapper countryMapper;

  @Override
  public PlutusResponse<List<CountryDto>> findAll() {
    List<CountryDto> countries =
        countryService.findAll().stream().map(countryMapper::mapToDto).collect(Collectors.toList());
    return new PlutusResponse<>(countries);
  }
}
