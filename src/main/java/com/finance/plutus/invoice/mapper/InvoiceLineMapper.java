package com.finance.plutus.invoice.mapper;

import com.finance.plutus.invoice.InvoiceCurrency;
import com.finance.plutus.invoice.InvoiceLine;
import com.finance.plutus.invoice.dto.CreateInvoiceLineDto;
import com.finance.plutus.invoice.dto.InvoiceLineDto;
import com.finance.plutus.invoice.dto.UpdateInvoiceLineDto;
import com.finance.plutus.item.Item;
import com.finance.plutus.item.ItemMapper;
import com.finance.plutus.item.ItemVat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

  public InvoiceLine mapToEntity(
      CreateInvoiceLineDto createInvoiceLineDto, InvoiceCurrency invoiceCurrency) {
    int quantity = createInvoiceLineDto.getQuantity();
    ItemVat vat = ItemVat.fromAmount(createInvoiceLineDto.getVat());
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUom(createInvoiceLineDto.getUom());
    invoiceLine.setVat(vat);
    invoiceLine.setQuantity(quantity);
    double rate = 1;
    InvoiceCurrency invoiceLineCurrency = null;
    if (invoiceCurrency != null) {
      rate = invoiceCurrency.getRate();
      invoiceLineCurrency = new InvoiceCurrency();
      invoiceLineCurrency.setRate(rate);
      invoiceLineCurrency.setValue(invoiceCurrency.getValue());
    }
    double unitPrice = createInvoiceLineDto.getUnitPrice() * rate;
    double subtotal = unitPrice * quantity;
    double total = vat.getAmount() * subtotal + subtotal;
    invoiceLine.setUnitPrice(unitPrice);
    invoiceLine.setSubtotal(subtotal);
    invoiceLine.setTotal(total);
    if (invoiceLineCurrency != null) {
      invoiceLineCurrency.setSubtotal(subtotal / rate);
      invoiceLineCurrency.setTotal(total / rate);
      invoiceLine.setCurrency(invoiceLineCurrency);
    }
    return invoiceLine;
  }

  public InvoiceLine mapToEntity(
      UpdateInvoiceLineDto updateInvoiceLineDto, InvoiceCurrency invoiceCurrency) {
    int quantity = updateInvoiceLineDto.getQuantity();
    ItemVat vat = ItemVat.fromAmount(updateInvoiceLineDto.getVat());
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUom(updateInvoiceLineDto.getUom());
    invoiceLine.setVat(vat);
    invoiceLine.setQuantity(quantity);
    double rate = 1;
    InvoiceCurrency invoiceLineCurrency = null;
    if (invoiceCurrency != null) {
      rate = invoiceCurrency.getRate();
      invoiceLineCurrency = new InvoiceCurrency();
      invoiceLineCurrency.setRate(rate);
      invoiceLineCurrency.setValue(invoiceCurrency.getValue());
    }
    double unitPrice = updateInvoiceLineDto.getUnitPrice() * rate;
    double subtotal = unitPrice * quantity;
    double total = vat.getAmount() * subtotal + subtotal;
    invoiceLine.setUnitPrice(unitPrice);
    invoiceLine.setSubtotal(subtotal);
    invoiceLine.setTotal(total);
    if (invoiceLineCurrency != null) {
      invoiceLineCurrency.setSubtotal(subtotal / rate);
      invoiceLineCurrency.setTotal(total / rate);
      invoiceLine.setCurrency(invoiceLineCurrency);
    }
    return invoiceLine;
  }
}
