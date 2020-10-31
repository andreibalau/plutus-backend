package com.finance.plutus.old.exception;

/** Plutus Created by catalin on 7/2/2020 */
public class EntityNotFoundException extends PlutusException {
  public EntityNotFoundException(String subject) {
    super(String.format("Requested %s was not found!", subject));
  }
}
