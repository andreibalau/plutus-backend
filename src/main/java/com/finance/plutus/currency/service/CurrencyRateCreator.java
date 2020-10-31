package com.finance.plutus.currency.service;

import com.finance.plutus.currency.model.CurrencyRateDto;

import java.util.List;

/** Plutus Created by Catalin on 11/1/2020 */
public interface CurrencyRateCreator {
  void create(CurrencyRateDto currencyRateDto);

  void create(List<CurrencyRateDto> currencyRateDtoList);
}
