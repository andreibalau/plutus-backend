package com.finance.plutus.transaction.exception;

import com.finance.plutus.app.exception.PlutusException;

/** Plutus Created by Catalin on 11/1/2020 */
public class WrongTransactionStatusException extends PlutusException {
  public WrongTransactionStatusException() {
    super("The current transaction status does not allow this operation!");
  }
}
