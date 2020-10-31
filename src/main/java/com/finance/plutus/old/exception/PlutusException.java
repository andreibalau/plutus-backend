package com.finance.plutus.old.exception;

/** Plutus Created by catalin on 7/1/2020 */
public class PlutusException extends RuntimeException {
  public PlutusException(String message) {
    super(message);
  }

  public PlutusException(String message, Throwable cause) {
    super(message, cause);
  }

  public PlutusException(Throwable cause) {
    super(cause);
  }

  public PlutusException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
