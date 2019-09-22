package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.model.transaction.dto.InvoiceDto;
import com.finance.plutus.repository.transaction.InvoiceRepository;
import com.finance.plutus.service.invoice.FindInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@Service
public class FindInvoiceImpl implements FindInvoice {

	private final InvoiceRepository invoiceRepository;

	@Override
	public List<InvoiceDto> findAll() {
		return invoiceRepository
				.findAll()
				.stream()
				.map(InvoiceDto::from)
				.collect(Collectors.toList());
	}
}
