package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.repository.InvoiceRepository;
import com.finance.plutus.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/3/2020 */
@Service
@RequiredArgsConstructor
public class FindInvoiceServiceImpl implements FindInvoiceService {

  private final InvoiceRepository invoiceRepository;

  @Override
  public InvoiceDto findDtoById(String id) {
    Invoice invoice = findEntityById(id);
    return InvoiceDto.mapFromEntity(invoice);
  }

  @Override
  public Invoice findEntityById(String id) {
    return invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("invoice"));
  }

  @Override
  public List<Invoice> findAllEntities() {
    return invoiceRepository.findAll();
  }

  @Override
  public List<InvoiceDto> findAllDtoByPage(int page, int size) {
    return invoiceRepository.findAll(PageRequest.of(page, size)).stream()
        .map(InvoiceDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long countAll() {
    return invoiceRepository.count();
  }
}
