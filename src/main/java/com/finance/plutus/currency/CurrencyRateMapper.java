package com.finance.plutus.currency;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 1/25/2021 */
@Component
public class CurrencyRateMapper {

  public CurrencyRateDto mapToDto(CurrencyRate currencyRate) {
    return CurrencyRateDto.builder()
        .currency(currencyRate.getCurrency())
        .date(currencyRate.getDate())
        .rate(currencyRate.getRate())
        .build();
  }

  public CurrencyRate mapToEntity(CurrencyRateDto currencyRateDto) {
    CurrencyRate currencyRate = new CurrencyRate();
    currencyRate.setCurrency(currencyRateDto.getCurrency());
    currencyRate.setDate(currencyRateDto.getDate());
    currencyRate.setRate(currencyRateDto.getRate());
    currencyRate.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    currencyRate.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    return currencyRate;
  }
}
