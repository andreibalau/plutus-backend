package com.finance.plutus.country.service;

import com.finance.plutus.country.model.CountryDto;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
public interface CountryService {
  CountryDto findByCode(String code);

  List<CountryDto> findAll();
}
