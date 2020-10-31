package com.finance.plutus.old.service.invoice;

import com.finance.plutus.old.model.dto.InvoiceDto;
import com.finance.plutus.old.model.entity.Invoice;

import java.util.List;
import java.util.UUID;

/** Plutus Created by catalin on 7/3/2020 */
public interface FindInvoiceService {
  InvoiceDto findDtoById(UUID id);

  Invoice findEntityById(UUID id);

  List<Invoice> findAllEntities(Iterable<UUID> ids);

  List<InvoiceDto> findAllDtoByPage(int page, int size);

  long countAll();
}
