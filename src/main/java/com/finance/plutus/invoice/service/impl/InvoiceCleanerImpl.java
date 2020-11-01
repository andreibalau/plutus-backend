package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.invoice.exception.WrongInvoiceStatusException;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceStatus;
import com.finance.plutus.invoice.repository.InvoiceRepository;
import com.finance.plutus.invoice.service.InvoiceCleaner;
import com.finance.plutus.invoice.service.InvoiceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceCleanerImpl implements InvoiceCleaner {

  private final InvoiceFinder invoiceFinder;
  private final InvoiceRepository invoiceRepository;

  @Override
  @Transactional
  public void delete(UUID id) {
    Invoice invoice = invoiceFinder.findById(id);
    if (invoice.getStatus() == InvoiceStatus.DONE) {
      throw new WrongInvoiceStatusException();
    }
    invoiceRepository.delete(invoice);
  }
}
