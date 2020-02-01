package com.finance.plutus.service.invoice.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.finance.plutus.exception.PartnerException;
import com.finance.plutus.exception.ProductException;
import com.finance.plutus.exception.SerialException;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.model.invoice.InvoiceLine;
import com.finance.plutus.model.invoice.dto.InvoiceLineDto;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.product.Product;
import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.repository.invoice.InvoiceRepository;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.repository.serial.SerialRepository;
import com.finance.plutus.service.invoice.CreateInvoiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Service
@RequiredArgsConstructor
public class CreateInvoiceServiceImpl implements CreateInvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final ProductRepository productRepository;
	private final PartnerRepository partnerRepository;
	private final SerialRepository serialRepository;
	private final ModelMapper modelMapper;

	@Override
	public EntityCreatedDto create(ModifyInvoiceDto invoiceDto) {
		Invoice invoice = new Invoice();
		invoice.setType(invoiceDto.getType());
		invoice.setCurrency(invoiceDto.getCurrency());
		invoice.setDate(invoiceDto.getDate());
		invoice.setName("draft " + System.currentTimeMillis());
		computeInvoiceLines(invoice, invoiceDto);
		Partner partner = findPartner(invoiceDto.getPartnerId());
		Serial serial = findSerial(invoiceDto.getSerialId());
		invoice.setSerial(serial);
		invoice.setPartner(partner);
		invoice.setCreatedOn(System.currentTimeMillis());
		invoice.setUpdatedOn(System.currentTimeMillis());
		return new EntityCreatedDto(invoiceRepository.save(invoice).getId());
	}

	private void computeInvoiceLines(Invoice invoice, ModifyInvoiceDto invoiceDto) {
		Set<InvoiceLine> invoiceLines = invoiceDto.getLines()
				.stream()
				.map(invoiceLineDto -> mapInvoiceLine(invoiceLineDto, invoice))
				.collect(Collectors.toSet());
		invoice.setLines(invoiceLines);
	}

	private InvoiceLine mapInvoiceLine(InvoiceLineDto invoiceLineDto, Invoice invoice) {
		InvoiceLine line = modelMapper.map(invoiceLineDto, InvoiceLine.class);
		line.setInvoice(invoice);
		line.setProduct(findProduct(invoiceLineDto.getProduct().getId()));
		line.setCreatedOn(System.currentTimeMillis());
		line.setUpdatedOn(System.currentTimeMillis());
		return line;
	}

	private Partner findPartner(Long partnerId) {
		return partnerRepository
				.findById(partnerId)
				.orElseThrow(PartnerException::partnerNotFound);
	}

	private Product findProduct(Long productId) {
		return productRepository
				.findById(productId)
				.orElseThrow(ProductException::productNotFound);
	}

	private Serial findSerial(Long serialId) {
		return serialRepository
				.findById(serialId)
				.orElseThrow(SerialException::serialNotFound);
	}

}
