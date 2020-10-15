package com.finance.plutus.model.html;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@RequiredArgsConstructor
public enum Template {
  INVOICE_NEW("invoice_new"),
  INVOICE("invoice");

  private final String value;
}
