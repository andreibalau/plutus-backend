package com.finance.plutus.currency.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.currency.model.Currency;
import com.finance.plutus.currency.model.CurrencyRate;
import com.finance.plutus.currency.repository.CurrencyRateRepository;
import com.finance.plutus.currency.service.CurrencyRateFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class CurrencyRateFinderImpl implements CurrencyRateFinder {

  private final CurrencyRateRepository currencyRateRepository;

  @Override
  public CurrencyRate findLastByDateAndCurrency(LocalDate date, Currency currency) {
    return currencyRateRepository
        .findTopByDateLessThanEqualAndCurrencyOrderByDateDesc(date, currency)
        .orElseThrow(() -> new EntityNotFoundException("currency rate"));
  }

  @Override
  public Optional<CurrencyRate> findByDateAndCurrency(LocalDate date, Currency currency) {
    return currencyRateRepository.findByDateAndCurrency(date, currency);
  }
}
