package com.finance.plutus.invoice.service;

import com.finance.plutus.invoice.model.CreateInvoiceDto;
import com.finance.plutus.invoice.model.InvoiceDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface InvoiceService {
  UUID create(CreateInvoiceDto invoice);

  InvoiceDto findById(UUID id);

  List<InvoiceDto> findAll(int page, int size);

  long count();

  void delete(UUID id);

  void collect(Iterable<UUID> ids);

  byte[] download(Iterable<UUID> ids);
}
