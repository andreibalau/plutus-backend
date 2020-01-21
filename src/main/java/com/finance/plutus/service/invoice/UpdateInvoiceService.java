package com.finance.plutus.service.invoice;

import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
public interface UpdateInvoiceService {
	void update(Long invoiceId, ModifyInvoiceDto invoiceDto);
}
