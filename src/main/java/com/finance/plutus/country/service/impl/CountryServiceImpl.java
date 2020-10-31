package com.finance.plutus.country.service.impl;

import com.finance.plutus.country.model.Country;
import com.finance.plutus.country.model.CountryDto;
import com.finance.plutus.country.service.CountryFinder;
import com.finance.plutus.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryFinder countryFinder;

  @Override
  public CountryDto findByCode(String code) {
    Country country = countryFinder.findByCode(code);
    return CountryDto.mapFromEntity(country);
  }

  @Override
  public List<CountryDto> findAll() {
    return countryFinder.findAll().stream()
        .map(CountryDto::mapFromEntity)
        .collect(Collectors.toList());
  }
}
