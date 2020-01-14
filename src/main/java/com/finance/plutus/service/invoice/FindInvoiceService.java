package com.finance.plutus.service.invoice;

import java.util.List;

import com.finance.plutus.model.invoice.dto.InvoiceDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface FindInvoiceService {
	List<InvoiceDto> findAll();
}
