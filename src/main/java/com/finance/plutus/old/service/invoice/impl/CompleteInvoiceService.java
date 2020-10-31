package com.finance.plutus.old.service.invoice.impl;

import com.finance.plutus.old.exception.WrongInvoiceStatusException;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.repository.InvoiceRepository;
import com.finance.plutus.old.service.invoice.FindInvoiceService;
import com.finance.plutus.old.service.invoice.InvoiceExecutableCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class CompleteInvoiceService implements InvoiceExecutableCommand {

  private final FindInvoiceService findInvoiceService;
  private final InvoiceRepository invoiceRepository;

  @Override
  @Transactional
  public void execute(UUID id) {
    Invoice invoice = findInvoiceService.findEntityById(id);
    execute(invoice);
  }

  @Override
  public void execute(Invoice invoice) {
    complete(invoice);
  }

  private void complete(Invoice invoice) {
    if (invoice.getStatus() != InvoiceStatus.ACTIVE) {
      throw new WrongInvoiceStatusException();
    }
    invoice.setStatus(InvoiceStatus.DONE);
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceRepository.save(invoice);
  }
}
