package com.finance.plutus.model.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class InvoiceLineDto {
  private Long id;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
  private PreviewItemDto item;
  private String uom;
  private Integer quantity;
  private Double price;
  private Double subtotal;
  private Double vat;
  private Double total;
}
