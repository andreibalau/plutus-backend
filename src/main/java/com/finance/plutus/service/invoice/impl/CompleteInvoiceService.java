package com.finance.plutus.service.invoice.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.finance.plutus.exception.WrongInvoiceStatusException;
import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.model.entity.InvoiceStatus;
import com.finance.plutus.repository.InvoiceRepository;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.service.invoice.InvoiceExecutableCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class CompleteInvoiceService implements InvoiceExecutableCommand {

  private final FindInvoiceService findInvoiceService;
  private final InvoiceRepository invoiceRepository;

  @Override
  @Transactional
  public void execute(Long id) {
    Invoice invoice = findInvoiceService.findById(id);
    if (invoice.getStatus() != InvoiceStatus.ACTIVE) {
      throw new WrongInvoiceStatusException();
    }
    invoice.setStatus(InvoiceStatus.DONE);
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceRepository.save(invoice);
  }
}
