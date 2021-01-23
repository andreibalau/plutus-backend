package com.finance.plutus.country;

import com.finance.plutus.app.payload.PlutusResponse;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
public interface CountryFacadeService {
  PlutusResponse<List<CountryDto>> findAll();
}
