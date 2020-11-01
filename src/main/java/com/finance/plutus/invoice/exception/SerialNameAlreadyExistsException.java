package com.finance.plutus.invoice.exception;

import com.finance.plutus.app.exception.PlutusException;

/** Plutus Created by catalin on 9/7/2020 */
public class SerialNameAlreadyExistsException extends PlutusException {
  public SerialNameAlreadyExistsException() {
    super("This serial name is already registered!");
  }
}
