package com.finance.plutus.service.invoice;

import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.dto.PreviewInvoiceDto;
import com.finance.plutus.model.entity.Invoice;

import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
public interface FindInvoiceService {
  InvoiceDto findDtoById(Long id);

  Invoice findById(Long id);

  List<PreviewInvoiceDto> findAllByPage(int page, int size);

  int countAll();
}
