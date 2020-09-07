package com.finance.plutus.exception;

/** Plutus Created by catalin on 9/7/2020 */
public class WrongInvoiceStatusException extends PlutusException {
  public WrongInvoiceStatusException() {
    super("The current invoice status does not allow this operation!");
  }
}
