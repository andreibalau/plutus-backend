package com.finance.plutus.model.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Plutus Created by Catalin on 10/12/2020 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Line {
  private String name;
  private Integer quantity;
  private String price;
  private String total;
}
