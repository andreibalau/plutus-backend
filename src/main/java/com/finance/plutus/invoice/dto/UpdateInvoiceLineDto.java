package com.finance.plutus.invoice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/** Plutus Created by Catalin on 1/25/2021 */
@Getter
@Setter
public class UpdateInvoiceLineDto {
  @NotNull private UUID itemId;
  @NotNull private Integer quantity;
  @NotNull private Double vat;
  @NotNull private Double unitPrice;
  private String uom;
  private String details;
}
