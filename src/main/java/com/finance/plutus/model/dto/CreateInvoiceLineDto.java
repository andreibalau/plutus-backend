package com.finance.plutus.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceLineDto {
  @NotNull private String itemId;
  @NotNull private Integer quantity;
  @NotNull private Double vat;
  @NotNull private Double unitPrice;
  private String uom;
}
