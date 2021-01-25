package com.finance.plutus.currency;

import org.springframework.stereotype.Component;

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
}
