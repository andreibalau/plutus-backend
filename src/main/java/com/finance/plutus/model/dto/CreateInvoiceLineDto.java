package com.finance.plutus.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceLineDto {
  @NotNull private Long item;
  @NotNull private Integer quantity;
  @NotNull private Double vat;
  @NotNull private Double price;
  private String uom;
}
