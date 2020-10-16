package com.finance.plutus.service.invoice;

import com.finance.plutus.model.dto.InvoiceHtmlDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
public interface DownloadInvoiceService {
  InvoiceHtmlDto downloadHtml(UUID id);

  List<InvoiceHtmlDto> downloadHtml(Iterable<UUID> ids);

  byte[] download(UUID id);

  byte[] download(Iterable<UUID> ids);
}
