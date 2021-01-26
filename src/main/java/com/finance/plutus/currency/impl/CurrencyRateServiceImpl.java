package com.finance.plutus.currency.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.currency.Currency;
import com.finance.plutus.currency.CurrencyRate;
import com.finance.plutus.currency.CurrencyRateDto;
import com.finance.plutus.currency.CurrencyRateMapper;
import com.finance.plutus.currency.CurrencyRateRepository;
import com.finance.plutus.currency.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

  private final CurrencyRateRepository currencyRateRepository;
  private final CurrencyRateMapper currencyRateMapper;

  @Override
  @Transactional
  public void create(CurrencyRateDto currencyRateDto) {
    CurrencyRate currencyRate =
        currencyRateRepository
            .findByDateAndCurrency(currencyRateDto.getDate(), currencyRateDto.getCurrency())
            .orElse(currencyRateMapper.mapToEntity(currencyRateDto));
    currencyRateRepository.save(currencyRate);
  }

  @Override
  public CurrencyRate findLastByDateAndCurrency(LocalDate date, Currency currency) {
    return currencyRateRepository
        .findTopByDateLessThanEqualAndCurrencyOrderByDateDesc(date, currency)
        .orElseThrow(() -> new EntityNotFoundException("currency rate"));
  }
}
