package com.finance.plutus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
@AllArgsConstructor
public class PreviewItemDto {
  private Long id;
  private String name;
  private Double unitPrice;
}
