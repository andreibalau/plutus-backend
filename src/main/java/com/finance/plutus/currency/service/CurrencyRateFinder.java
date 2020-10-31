package com.finance.plutus.currency.service;

import com.finance.plutus.currency.model.Currency;
import com.finance.plutus.currency.model.CurrencyRate;

import java.time.LocalDate;
import java.util.Optional;

/** Plutus Created by Catalin on 11/1/2020 */
public interface CurrencyRateFinder {
  CurrencyRate findLastByDateAndCurrency(LocalDate date, Currency currency);

  Optional<CurrencyRate> findByDateAndCurrency(LocalDate date, Currency currency);
}
