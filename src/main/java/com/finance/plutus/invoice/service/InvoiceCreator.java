package com.finance.plutus.invoice.service;

import com.finance.plutus.old.model.dto.CreateInvoiceDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface InvoiceCreator {
  UUID create(CreateInvoiceDto invoice);
}
