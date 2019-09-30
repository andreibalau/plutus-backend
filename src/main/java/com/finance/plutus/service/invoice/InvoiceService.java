package com.finance.plutus.service.invoice;

import com.finance.plutus.model.transaction.dto.InvoiceDto;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface InvoiceService {
	List<InvoiceDto> findAll();
}
