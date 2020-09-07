package com.finance.plutus.exception;

/** Plutus Created by catalin on 9/7/2020 */
public class SerialNameAlreadyExistsException extends PlutusException {
  public SerialNameAlreadyExistsException() {
    super("This serial name is already registered!");
  }
}
