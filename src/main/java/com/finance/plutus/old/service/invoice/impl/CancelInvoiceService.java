package com.finance.plutus.old.service.invoice.impl;

import com.finance.plutus.old.exception.WrongInvoiceStatusException;
import com.finance.plutus.old.model.entity.Invoice;
import com.finance.plutus.old.repository.InvoiceRepository;
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
public class CancelInvoiceService implements InvoiceExecutableCommand {

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
    cancel(invoice);
  }

  private void cancel(Invoice invoice) {
    if (invoice.getStatus() != InvoiceStatus.DRAFT) {
      throw new WrongInvoiceStatusException();
    }
    invoice.setStatus(InvoiceStatus.CANCELLED);
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceRepository.save(invoice);
  }
}
