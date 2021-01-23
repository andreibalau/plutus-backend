package com.finance.plutus.invoice.mapper;

import com.finance.plutus.invoice.InvoiceLine;
import com.finance.plutus.invoice.dto.InvoiceLineDto;
import com.finance.plutus.item.Item;
import com.finance.plutus.item.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
@RequiredArgsConstructor
public class InvoiceLineMapper {

  private final ItemMapper itemMapper;
  private final InvoiceCurrencyMapper invoiceCurrencyMapper;

  public InvoiceLineDto mapToDto(InvoiceLine invoiceLine) {
    Item item = invoiceLine.getItem();
    InvoiceLineDto invoiceLineDto = new InvoiceLineDto();
    invoiceLineDto.setCreatedOn(invoiceLine.getCreatedOn());
    invoiceLineDto.setUpdatedOn(invoiceLine.getUpdatedOn());
    invoiceLineDto.setId(invoiceLine.getId().toString());
    invoiceLineDto.setItem(itemMapper.mapToDto(item));
    invoiceLineDto.setUnitPrice(invoiceLine.getUnitPrice());
    invoiceLineDto.setQuantity(invoiceLine.getQuantity());
    invoiceLineDto.setSubtotal(invoiceLine.getSubtotal());
    invoiceLineDto.setVat(invoiceLine.getVat().getAmountPercent());
    invoiceLineDto.setTotal(invoiceLine.getTotal());
    invoiceLineDto.setUom(invoiceLine.getUom());
    invoiceLineDto.setCurrency(
        Optional.ofNullable(invoiceLine.getCurrency())
            .map(invoiceCurrencyMapper::mapToDto)
            .orElse(null));
    return invoiceLineDto;
  }
}
