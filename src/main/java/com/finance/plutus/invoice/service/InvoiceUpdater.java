package com.finance.plutus.invoice.service;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface InvoiceUpdater {
  void markAsDone(UUID id);
}
