package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/** Plutus Created by Catalin on 9/27/2020 */
@Getter
@RequiredArgsConstructor
public enum ItemVat {
  ZERO(0.00),
  FIVE(5.00),
  NINE(9.00),
  NINETEEN(19.00);

  private final Double amount;

  public static ItemVat fromAmount(Double amount) {
    return Arrays.stream(ItemVat.values())
        .filter(itemVat -> itemVat.amount.equals(amount))
        .findAny()
        .orElseThrow();
  }
}
