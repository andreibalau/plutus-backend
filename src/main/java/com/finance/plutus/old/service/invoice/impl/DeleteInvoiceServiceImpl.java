package com.finance.plutus.old.service.invoice.impl;

import com.finance.plutus.old.model.entity.Invoice;
import com.finance.plutus.old.repository.InvoiceRepository;
import com.finance.plutus.old.service.invoice.DeleteInvoiceService;
import com.finance.plutus.old.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by catalin on 7/3/2020 */
@Service
@RequiredArgsConstructor
public class DeleteInvoiceServiceImpl implements DeleteInvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final FindInvoiceService findInvoiceService;

  @Override
  @Transactional
  public void delete(UUID id) {
    Invoice invoice = findInvoiceService.findEntityById(id);
    invoiceRepository.delete(invoice);
  }
}
