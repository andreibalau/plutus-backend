package com.finance.plutus.service.country.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.CountryDto;
import com.finance.plutus.model.entity.Country;
import com.finance.plutus.repository.CountryRepository;
import com.finance.plutus.service.country.FindCountryService;
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
