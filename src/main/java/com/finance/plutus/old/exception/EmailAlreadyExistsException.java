package com.finance.plutus.old.exception;

import com.finance.plutus.app.exception.PlutusException;

/** Plutus Created by Catalin on 7/1/2020 */
public class EmailAlreadyExistsException extends PlutusException {
  public EmailAlreadyExistsException() {
    super("Email already exists!");
  }
}
