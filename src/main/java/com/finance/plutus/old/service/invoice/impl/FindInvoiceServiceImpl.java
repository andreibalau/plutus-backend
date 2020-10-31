package com.finance.plutus.old.service.invoice.impl;

import com.finance.plutus.old.exception.EntityNotFoundException;
import com.finance.plutus.old.model.dto.InvoiceDto;
import com.finance.plutus.old.model.entity.Invoice;
import com.finance.plutus.old.repository.InvoiceRepository;
import com.finance.plutus.old.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/3/2020 */
@Service
@RequiredArgsConstructor
public class FindInvoiceServiceImpl implements FindInvoiceService {

  private final InvoiceRepository invoiceRepository;

  @Override
  public InvoiceDto findDtoById(UUID id) {
    Invoice invoice = findEntityById(id);
    return InvoiceDto.mapFromEntity(invoice);
  }

  @Override
  public Invoice findEntityById(UUID id) {
    return invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("invoice"));
  }

  @Override
  public List<Invoice> findAllEntities(Iterable<UUID> ids) {
    return invoiceRepository.findAllById(ids);
  }

  @Override
  public List<InvoiceDto> findAllDtoByPage(int page, int size) {
    return invoiceRepository.findAll(PageRequest.of(page, size, Sort.by("createdOn"))).stream()
        .map(InvoiceDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long countAll() {
    return invoiceRepository.count();
  }
}
