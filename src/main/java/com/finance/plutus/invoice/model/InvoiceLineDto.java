package com.finance.plutus.invoice.model;

import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.model.ItemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

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
  private InvoiceCurrencyDto currency;

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
    invoiceLineDto.setCurrency(
        Optional.ofNullable(invoiceLine.getCurrency())
            .map(InvoiceCurrencyDto::mapFromEntity)
            .orElse(null));
    return invoiceLineDto;
  }
}
