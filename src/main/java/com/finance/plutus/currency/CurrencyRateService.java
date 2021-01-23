package com.finance.plutus.currency;

import java.time.LocalDate;

/** Plutus Created by Catalin on 1/23/2021 */
public interface CurrencyRateService {
  void create(CurrencyRateDto currencyRateDto);

  CurrencyRate findLastByDateAndCurrency(LocalDate date, Currency currency);
}
