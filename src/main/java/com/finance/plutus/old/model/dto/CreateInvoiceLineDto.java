package com.finance.plutus.old.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceLineDto {
  @NotNull private UUID itemId;
  @NotNull private Integer quantity;
  @NotNull private Double vat;
  @NotNull private Double unitPrice;
  private String uom;
}
