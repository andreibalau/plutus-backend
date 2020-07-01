package com.finance.plutus.exception;

/** Plutus Created by Catalin on 7/1/2020 */
public class WrongCredentialsException extends PlutusException {
  public WrongCredentialsException() {
    super("Please check your credentials!");
  }
}
