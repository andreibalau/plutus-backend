package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.model.transaction.dto.InvoiceDto;
import com.finance.plutus.service.invoice.FindInvoice;
import com.finance.plutus.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

	private final FindInvoice findInvoice;

	@Override
	public List<InvoiceDto> findAll() {
		return findInvoice.findAll();
	}
}
