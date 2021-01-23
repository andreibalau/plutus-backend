package com.finance.plutus.invoice.dto;

import com.finance.plutus.currency.Currency;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 11/1/2020 */
@Getter
@Setter
public class InvoiceCurrencyDto {
  private Currency currency;
  private Double rate;
  private Double subtotal;
  private Double total;
}
