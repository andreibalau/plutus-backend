package com.finance.plutus.country.service;

import com.finance.plutus.country.model.Country;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
public interface CountryFinder {
  Country findByCode(String code);

  List<Country> findAll();
}
