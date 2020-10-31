package com.finance.plutus.old.service.country.impl;

import com.finance.plutus.old.exception.EntityNotFoundException;
import com.finance.plutus.country.model.CountryDto;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.country.repository.CountryRepository;
import com.finance.plutus.old.service.country.FindCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 9/27/2020 */
@Service
@RequiredArgsConstructor
public class FindCountryServiceImpl implements FindCountryService {

  private final CountryRepository countryRepository;

  @Override
  public CountryDto findDtoByCode(String code) {
    Country country = findEntityByCode(code);
    return CountryDto.mapFromEntity(country);
  }

  @Override
  public Country findEntityByCode(String code) {
    return countryRepository
        .findById(code)
        .orElseThrow(() -> new EntityNotFoundException("country"));
  }

  @Override
  public List<CountryDto> findAllDto() {
    return countryRepository.findAll().stream()
        .map(CountryDto::mapFromEntity)
        .collect(Collectors.toList());
  }
}
