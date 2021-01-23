package com.finance.plutus.invoice.dto;

import com.finance.plutus.item.dto.ItemDto;
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
  private InvoiceCurrencyDto currency;
}
