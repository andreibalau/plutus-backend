package com.finance.plutus.item.model;

import com.finance.plutus.old.exception.WrongVatAmountException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/** Plutus Created by Catalin on 9/27/2020 */
@RequiredArgsConstructor
public enum ItemVat {
  ZERO(0.00),
  FIVE(5.00),
  NINE(9.00),
  NINETEEN(19.00);

  private final Double amount;

  public Double getAmount() {
    return amount / 100;
  }

  public Double getAmountPercent() {
    return amount;
  }

  public static ItemVat fromAmount(Double amount) {
    return Arrays.stream(ItemVat.values())
        .filter(itemVat -> itemVat.amount.equals(amount))
        .findAny()
        .orElseThrow(WrongVatAmountException::new);
  }
}
