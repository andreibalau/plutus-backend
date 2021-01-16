package com.finance.plutus.app.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@RequiredArgsConstructor
public enum Template {
  INVOICE("invoice_new"),
  TRANSACTIONS("transactions_report");

  private final String value;
}
