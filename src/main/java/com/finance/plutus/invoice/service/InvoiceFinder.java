package com.finance.plutus.invoice.service;

import com.finance.plutus.invoice.model.Invoice;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface InvoiceFinder {
  Invoice findById(UUID id);

  List<Invoice> findAllById(Iterable<UUID> ids);

  List<Invoice> findAll(PageRequest page);

  List<Invoice> findAll();

  long count();
}
