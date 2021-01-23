package com.finance.plutus.invoice.mapper;

import com.finance.plutus.invoice.InvoiceCurrency;
import com.finance.plutus.invoice.dto.InvoiceCurrencyDto;
import org.springframework.stereotype.Component;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
public class InvoiceCurrencyMapper {

  public InvoiceCurrencyDto mapToDto(InvoiceCurrency invoiceCurrency) {
    InvoiceCurrencyDto invoiceCurrencyDto = new InvoiceCurrencyDto();
    invoiceCurrencyDto.setCurrency(invoiceCurrency.getValue());
    invoiceCurrencyDto.setRate(invoiceCurrency.getRate());
    invoiceCurrencyDto.setSubtotal(invoiceCurrency.getSubtotal());
    invoiceCurrencyDto.setTotal(invoiceCurrency.getTotal());
    return invoiceCurrencyDto;
  }
}
