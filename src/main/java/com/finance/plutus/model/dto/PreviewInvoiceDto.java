package com.finance.plutus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
@AllArgsConstructor
public class PreviewInvoiceDto {
  private Long id;
  private String name;
  private Double total;
}
