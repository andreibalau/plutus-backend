package com.finance.plutus.old.model.dto;

import com.finance.plutus.old.model.entity.Currency;
import com.finance.plutus.old.model.entity.CurrencyRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/** Plutus Created by Catalin on 10/4/2020 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateDto {
  private LocalDate date;
  private Currency currency;
  private Double rate;

  public static CurrencyRateDto mapFromEntity(CurrencyRate currencyRate) {
    return CurrencyRateDto.builder()
        .date(currencyRate.getDate())
        .currency(currencyRate.getCurrency())
        .rate(currencyRate.getRate())
        .build();
  }
}
