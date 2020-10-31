package com.finance.plutus.country.service;

import com.finance.plutus.country.model.CountryDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface CountryService {
  CountryDto findByCode(String code);

  List<CountryDto> findAll();
}
