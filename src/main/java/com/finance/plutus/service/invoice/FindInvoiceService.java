package com.finance.plutus.service.invoice;

import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.entity.Invoice;

import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
public interface FindInvoiceService {
  InvoiceDto findDtoById(String id);

  Invoice findEntityById(String id);

  List<Invoice> findAllEntitiesByIds(Iterable<String> ids);

  List<InvoiceDto> findAllDtoByPage(int page, int size);

  long countAll();
}
