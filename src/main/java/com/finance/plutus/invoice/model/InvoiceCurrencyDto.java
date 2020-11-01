package com.finance.plutus.invoice.model;

import com.finance.plutus.currency.model.Currency;
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

  public static InvoiceCurrencyDto mapFromEntity(InvoiceCurrency invoiceCurrency) {
    InvoiceCurrencyDto invoiceCurrencyDto = new InvoiceCurrencyDto();
    invoiceCurrencyDto.setCurrency(invoiceCurrency.getValue());
    invoiceCurrencyDto.setRate(invoiceCurrency.getRate());
    invoiceCurrencyDto.setSubtotal(invoiceCurrency.getSubtotal());
    invoiceCurrencyDto.setTotal(invoiceCurrency.getTotal());
    return invoiceCurrencyDto;
  }
}
