package com.finance.plutus.currency;

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
}
