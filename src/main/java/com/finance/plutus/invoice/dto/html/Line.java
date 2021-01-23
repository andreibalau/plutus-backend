package com.finance.plutus.invoice.dto.html;

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
  private String number;
  private String name;
  private String details;
  private String price;
  private Integer quantity;
  private String total;
}
