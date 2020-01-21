package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.exception.InvoiceException;
import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.repository.invoice.InvoiceRepository;
import com.finance.plutus.service.invoice.DeleteInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Service
@RequiredArgsConstructor
public class DeleteInvoiceServiceImpl implements DeleteInvoiceService {

	private final InvoiceRepository invoiceRepository;

	@Override
	public void delete(Long invoiceId) {
		Invoice invoice = findInvoice(invoiceId);
		invoiceRepository.delete(invoice);
	}

	private Invoice findInvoice(Long invoiceId) {
		return invoiceRepository
				.findById(invoiceId)
				.orElseThrow(InvoiceException::invoiceNotFound);
	}

}
