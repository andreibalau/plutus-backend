package com.finance.plutus.service.invoice;

import java.util.List;

import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.model.invoice.dto.PreviewInvoiceDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface FindInvoiceService {
	List<PreviewInvoiceDto> findAll();
	InvoiceDto findById(Long invoiceId);
}
