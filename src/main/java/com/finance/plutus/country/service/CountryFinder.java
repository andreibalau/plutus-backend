package com.finance.plutus.country.service;

import com.finance.plutus.country.model.Country;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface CountryFinder {
  Country findById(UUID id);

  List<Country> findAll();
}
