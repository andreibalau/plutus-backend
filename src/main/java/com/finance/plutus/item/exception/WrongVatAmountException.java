package com.finance.plutus.item.exception;

import com.finance.plutus.app.exception.PlutusException;

/** Plutus Created by Catalin on 9/27/2020 */
public class WrongVatAmountException extends PlutusException {
  public WrongVatAmountException() {
    super("The provided amount is wrong!");
  }
}
