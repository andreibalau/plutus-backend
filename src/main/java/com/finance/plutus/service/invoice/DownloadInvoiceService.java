package com.finance.plutus.service.invoice;

/** Plutus Created by catalin on 9/7/2020 */
public interface DownloadInvoiceService {
  byte[] download(String id);

  byte[] downloadAll(Iterable<String> ids);
}
