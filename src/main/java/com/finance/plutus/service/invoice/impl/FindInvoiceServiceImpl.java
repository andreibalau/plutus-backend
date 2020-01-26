package com.finance.plutus.service.invoice.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.exception.InvoiceException;
import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.model.invoice.dto.InvoiceLineDto;
import com.finance.plutus.model.invoice.dto.PreviewInvoiceDto;
import com.finance.plutus.model.partner.dto.PreviewPartnerDto;
import com.finance.plutus.model.product.dto.ProductDto;
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
				.map(this::mapInvoice)
				.orElseThrow(InvoiceException::invoiceNotFound);
	}

	private InvoiceDto mapInvoice(Invoice invoice) {
		InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
		invoice.getLines().forEach(invoiceLine -> {
			InvoiceLineDto invoiceLineDto = modelMapper.map(invoiceLine, InvoiceLineDto.class);
			invoiceLineDto.setProduct(modelMapper.map(invoiceLine.getProduct(), ProductDto.class));
			invoiceDto.getLines().add(invoiceLineDto);
		});
		invoiceDto.setClient(modelMapper.map(invoice.getClient(), PreviewPartnerDto.class));
		invoiceDto.setVendor(modelMapper.map(invoice.getVendor(), PreviewPartnerDto.class));
		return invoiceDto;
	}
}
