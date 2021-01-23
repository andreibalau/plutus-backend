package com.finance.plutus.country;

import com.finance.plutus.country.Country;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
public interface CountryService {
  Country findByCode(String code);

  List<Country> findAll();
}
