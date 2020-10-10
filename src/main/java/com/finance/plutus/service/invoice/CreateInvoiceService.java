package com.finance.plutus.service.invoice;

import com.finance.plutus.model.dto.CreateInvoiceDto;

import java.util.UUID;

/** Plutus Created by catalin on 7/3/2020 */
public interface CreateInvoiceService {
  UUID create(CreateInvoiceDto createInvoiceDto);
}
