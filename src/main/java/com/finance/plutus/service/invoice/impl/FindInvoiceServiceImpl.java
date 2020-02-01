package com.finance.plutus.service.invoice.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.exception.InvoiceException;
import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.model.invoice.dto.PreviewInvoiceDto;
import com.finance.plutus.repository.invoice.InvoiceRepository;
import com.finance.plutus.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@Service
public class FindInvoiceServiceImpl implements FindInvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<PreviewInvoiceDto> findAll() {
		return invoiceRepository
				.findAll()
				.stream()
				.map(invoice -> modelMapper.map(invoice, PreviewInvoiceDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public InvoiceDto findById(Long invoiceId) {
		return invoiceRepository
				.findById(invoiceId)
				.map(invoice -> modelMapper.map(invoice, InvoiceDto.class))
				.orElseThrow(InvoiceException::invoiceNotFound);
	}
	
}
