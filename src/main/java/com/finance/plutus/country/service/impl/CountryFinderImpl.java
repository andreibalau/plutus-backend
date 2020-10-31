package com.finance.plutus.country.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.country.repository.CountryRepository;
import com.finance.plutus.country.service.CountryFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class CountryFinderImpl implements CountryFinder {

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
