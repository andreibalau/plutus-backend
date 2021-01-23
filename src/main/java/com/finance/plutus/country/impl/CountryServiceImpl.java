package com.finance.plutus.country.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.country.Country;
import com.finance.plutus.country.CountryRepository;
import com.finance.plutus.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;

  @Override
  public Country findByCode(String code) {
    return countryRepository
        .findByCode(code)
        .orElseThrow(() -> new EntityNotFoundException("country"));
  }

  @Override
  public List<Country> findAll() {
    return countryRepository.findAll();
  }
}
