package com.finance.plutus.old.model.dto;

import com.finance.plutus.old.model.entity.Currency;
import com.finance.plutus.old.model.entity.InvoiceLine;
import com.finance.plutus.old.model.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class InvoiceLineDto {
  private String id;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
  private ItemDto item;
  private String uom;
  private Integer quantity;
  private Double unitPrice;
  private Double subtotal;
  private Double vat;
  private Double total;
  private Currency currency;
  private Double currencySubtotal;
  private Double currencyUnitPrice;
  private Double currencyTotal;

  public static InvoiceLineDto mapFromEntity(InvoiceLine invoiceLine) {
    Item item = invoiceLine.getItem();
    InvoiceLineDto invoiceLineDto = new InvoiceLineDto();
    invoiceLineDto.setCreatedOn(invoiceLine.getCreatedOn());
    invoiceLineDto.setUpdatedOn(invoiceLine.getUpdatedOn());
    invoiceLineDto.setId(invoiceLine.getId().toString());
    invoiceLineDto.setItem(ItemDto.mapFromEntity(item));
    invoiceLineDto.setUnitPrice(invoiceLine.getUnitPrice());
    invoiceLineDto.setQuantity(invoiceLine.getQuantity());
    invoiceLineDto.setSubtotal(invoiceLine.getSubtotal());
    invoiceLineDto.setVat(invoiceLine.getVat().getAmountPercent());
    invoiceLineDto.setTotal(invoiceLine.getTotal());
    invoiceLineDto.setUom(invoiceLine.getUom());
    invoiceLineDto.setCurrency(invoiceLine.getCurrency());
    invoiceLineDto.setCurrencySubtotal(invoiceLine.getCurrencySubtotal());
    invoiceLineDto.setCurrencyTotal(invoiceLine.getCurrencyTotal());
    invoiceLineDto.setCurrencyUnitPrice(invoiceLine.getCurrencyUnitPrice());
    return invoiceLineDto;
  }
}
