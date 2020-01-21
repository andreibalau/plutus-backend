package com.finance.plutus.service.invoice;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
public interface CreateInvoiceService {
	EntityCreatedDto create(ModifyInvoiceDto invoiceDto);
}
