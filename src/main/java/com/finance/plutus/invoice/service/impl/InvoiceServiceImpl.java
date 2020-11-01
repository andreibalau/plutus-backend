package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.invoice.model.CreateInvoiceDto;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceDto;
import com.finance.plutus.invoice.service.InvoiceCleaner;
import com.finance.plutus.invoice.service.InvoiceCreator;
import com.finance.plutus.invoice.service.InvoiceDownloader;
import com.finance.plutus.invoice.service.InvoiceFinder;
import com.finance.plutus.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceCreator invoiceCreator;
  private final InvoiceFinder invoiceFinder;
  private final InvoiceCleaner invoiceCleaner;
  private final InvoiceDownloader invoiceDownloader;

  @Override
  public UUID create(CreateInvoiceDto invoice) {
    return invoiceCreator.create(invoice);
  }

  @Override
  public InvoiceDto findById(UUID id) {
    Invoice invoice = invoiceFinder.findById(id);
    return InvoiceDto.mapFromEntity(invoice);
  }

  @Override
  public List<InvoiceDto> findAllById(Iterable<UUID> ids) {
    return invoiceFinder.findAllById(ids).stream()
        .map(InvoiceDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<InvoiceDto> findAll(int page, int size) {
    return invoiceFinder.findAll(PageRequest.of(page, size)).stream()
        .map(InvoiceDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long count() {
    return invoiceFinder.count();
  }

  @Override
  public void delete(UUID id) {
    invoiceCleaner.delete(id);
  }

  @Override
  public byte[] download(UUID id) {
    return invoiceDownloader.download(id);
  }

  @Override
  public byte[] download(Iterable<UUID> ids) {
    return invoiceDownloader.download(ids);
  }
}
