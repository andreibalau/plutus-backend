package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceStatus;
import com.finance.plutus.invoice.repository.InvoiceRepository;
import com.finance.plutus.invoice.service.InvoiceFinder;
import com.finance.plutus.invoice.service.InvoiceUpdater;
import com.finance.plutus.transaction.model.CreateTransactionDto;
import com.finance.plutus.transaction.model.TransactionMethod;
import com.finance.plutus.transaction.model.TransactionType;
import com.finance.plutus.transaction.service.TransactionCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceUpdaterImpl implements InvoiceUpdater {

  private final InvoiceFinder invoiceFinder;
  private final InvoiceRepository invoiceRepository;
  private final TransactionCreator transactionCreator;

  @Override
  @Transactional
  public void markAsDone(UUID id) {
    Invoice invoice = invoiceFinder.findById(id);
    markAsDone(invoice);
  }

  @Override
  @Transactional
  public void markAsDone(Iterable<UUID> ids) {
    List<Invoice> invoices = invoiceFinder.findAllById(ids);
    invoices.forEach(this::markAsDone);
  }

  private void markAsDone(Invoice invoice) {
    if (invoice.getStatus() != InvoiceStatus.DONE) {
      invoice.setStatus(InvoiceStatus.DONE);
      invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      invoiceRepository.save(invoice);
      createTransaction(invoice);
    }
  }

  private void createTransaction(Invoice invoice) {
    CreateTransactionDto createTransactionDto = new CreateTransactionDto();
    createTransactionDto.setDate(invoice.getDate());
    createTransactionDto.setMethod(TransactionMethod.BANK);
    createTransactionDto.setType(TransactionType.INCOME);
    createTransactionDto.setPartnerId(invoice.getClient().getId());
    createTransactionDto.setValue(invoice.getTotal());
    createTransactionDto.setDocument(String.format("Factura %s", invoice.getName()));
    createTransactionDto.setDetails("Incasare client Upwork");
    transactionCreator.create(createTransactionDto);
  }
}
