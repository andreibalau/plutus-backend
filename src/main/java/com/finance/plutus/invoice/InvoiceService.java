package com.finance.plutus.invoice;

import com.finance.plutus.invoice.dto.CreateInvoiceDto;
import com.finance.plutus.invoice.dto.UpdateInvoiceDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface InvoiceService {
  UUID create(CreateInvoiceDto createInvoiceDto);

  void update(UUID id, UpdateInvoiceDto updateInvoiceDto);

  void collect(Iterable<UUID> ids);

  void collect(UUID id);

  Invoice findById(UUID id);

  List<Invoice> findAllById(Iterable<UUID> ids);

  List<Invoice> findAll(int page, int size);

  List<Invoice> findAll();

  void delete(UUID id);

  byte[] download(UUID id);

  byte[] downloadAll();
}
