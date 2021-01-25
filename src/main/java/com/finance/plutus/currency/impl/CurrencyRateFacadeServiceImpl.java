package com.finance.plutus.currency.impl;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.currency.Currency;
import com.finance.plutus.currency.CurrencyRate;
import com.finance.plutus.currency.CurrencyRateDto;
import com.finance.plutus.currency.CurrencyRateFacadeService;
import com.finance.plutus.currency.CurrencyRateMapper;
import com.finance.plutus.currency.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/** Plutus Created by Catalin on 1/25/2021 */
@Service
@RequiredArgsConstructor
public class CurrencyRateFacadeServiceImpl implements CurrencyRateFacadeService {

  private final CurrencyRateService currencyRateService;
  private final CurrencyRateMapper currencyRateMapper;

  @Override
  public PlutusResponse<List<CurrencyRateDto>> fetchTodayRates() {
    LocalDate now = LocalDate.now();
    CurrencyRate currencyRateUsd = currencyRateService.findLastByDateAndCurrency(now, Currency.USD);
    CurrencyRate currencyRateEur = currencyRateService.findLastByDateAndCurrency(now, Currency.EUR);
    return new PlutusResponse<>(
        List.of(
            currencyRateMapper.mapToDto(currencyRateEur),
            currencyRateMapper.mapToDto(currencyRateUsd)));
  }
}
