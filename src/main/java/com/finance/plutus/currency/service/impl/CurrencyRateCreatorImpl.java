package com.finance.plutus.currency.service.impl;

import com.finance.plutus.currency.model.CurrencyRate;
import com.finance.plutus.currency.model.CurrencyRateDto;
import com.finance.plutus.currency.repository.CurrencyRateRepository;
import com.finance.plutus.currency.service.CurrencyRateCreator;
import com.finance.plutus.currency.service.CurrencyRateFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class CurrencyRateCreatorImpl implements CurrencyRateCreator {

  private final CurrencyRateRepository currencyRateRepository;
  private final CurrencyRateFinder currencyRateFinder;

  @Override
  @Transactional
  public void create(CurrencyRateDto currencyRateDto) {
    CurrencyRate currencyRate =
        currencyRateFinder
            .findByDateAndCurrency(currencyRateDto.getDate(), currencyRateDto.getCurrency())
            .orElse(createCurrencyRate(currencyRateDto));
    currencyRateRepository.save(currencyRate);
  }

  @Override
  @Transactional
  public void create(List<CurrencyRateDto> currencyRateDtoList) {
    currencyRateDtoList.forEach(this::create);
  }

  private CurrencyRate createCurrencyRate(CurrencyRateDto currencyRateDto) {
    CurrencyRate currencyRate = new CurrencyRate();
    currencyRate.setCurrency(currencyRateDto.getCurrency());
    currencyRate.setDate(currencyRateDto.getDate());
    currencyRate.setRate(currencyRateDto.getRate());
    return currencyRate;
  }
}
