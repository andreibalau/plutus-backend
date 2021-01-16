package com.finance.plutus.invoice.service;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface InvoiceDownloader {
  byte[] download(Iterable<UUID> ids);

  byte[] downloadAll();
}
