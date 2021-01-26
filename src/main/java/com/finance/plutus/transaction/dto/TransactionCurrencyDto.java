package com.finance.plutus.transaction.dto;

import com.finance.plutus.currency.Currency;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 1/26/2021 */
@Getter
@Setter
public class TransactionCurrencyDto {
  private Currency currency;
  private Double value;
}
