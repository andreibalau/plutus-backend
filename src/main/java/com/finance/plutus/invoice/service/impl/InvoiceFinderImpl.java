package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.repository.InvoiceRepository;
import com.finance.plutus.invoice.service.InvoiceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceFinderImpl implements InvoiceFinder {

  private final InvoiceRepository invoiceRepository;

  @Override
  public Invoice findById(UUID id) {
    return invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("invoice"));
  }

  @Override
  public List<Invoice> findAllById(Iterable<UUID> ids) {
    return invoiceRepository.findAllById(ids);
  }

  @Override
  public List<Invoice> findAll(PageRequest page) {
    return invoiceRepository.findAll(page).stream().collect(Collectors.toList());
  }

  @Override
  public List<Invoice> findAll() {
    return invoiceRepository.findAll();
  }

  @Override
  public long count() {
    return invoiceRepository.count();
  }
}
