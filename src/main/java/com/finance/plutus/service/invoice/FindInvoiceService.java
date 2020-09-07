package com.finance.plutus.service.invoice;

import java.util.List;

import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.dto.PreviewInvoiceDto;
import com.finance.plutus.model.entity.Invoice;

/** Plutus Created by catalin on 7/3/2020 */
public interface FindInvoiceService {
  InvoiceDto findDtoById(Long id);

  Invoice findById(Long id);

  List<Invoice> findAllByIds(Iterable<Long> ids);

  List<PreviewInvoiceDto> findAllByPage(int page, int size);

  int countAll();
}
