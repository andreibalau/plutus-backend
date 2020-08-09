package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.repository.InvoiceRepository;
import com.finance.plutus.service.invoice.DeleteInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/3/2020 */
@Service
@RequiredArgsConstructor
public class DeleteInvoiceServiceImpl implements DeleteInvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final FindInvoiceService findInvoiceService;

  @Override
  public void delete(Long id) {
    Invoice invoice = findInvoiceService.findById(id);
    invoiceRepository.delete(invoice);
  }
}
