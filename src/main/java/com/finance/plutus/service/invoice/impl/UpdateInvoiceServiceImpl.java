package com.finance.plutus.service.invoice.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.finance.plutus.exception.InvoiceException;
import com.finance.plutus.exception.PartnerException;
import com.finance.plutus.exception.ProductException;
import com.finance.plutus.exception.SerialException;
import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.model.invoice.InvoiceLine;
import com.finance.plutus.model.invoice.Status;
import com.finance.plutus.model.invoice.dto.InvoiceLineDto;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.product.Product;
import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.repository.invoice.InvoiceRepository;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.repository.serial.SerialRepository;
import com.finance.plutus.service.invoice.UpdateInvoiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Service
@RequiredArgsConstructor
public class UpdateInvoiceServiceImpl implements UpdateInvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final ProductRepository productRepository;
	private final PartnerRepository partnerRepository;
	private final SerialRepository serialRepository;
	private final ModelMapper modelMapper;

	@Override
	public void update(Long invoiceId, ModifyInvoiceDto invoiceDto) {
		Invoice invoice = findInvoice(invoiceId);
		checkIfInvoiceCanBeUpdated(invoice);
		updateInvoice(invoice, invoiceDto);
	}

	private void checkIfInvoiceCanBeUpdated(Invoice invoice) {
		if (invoice.getStatus() != Status.DRAFT) {
			throw InvoiceException.invoiceCannotBeUpdated();
		}
	}

	private void updateInvoice(Invoice invoice, ModifyInvoiceDto invoiceDto) {
		computeInvoiceLines(invoice, invoiceDto);
		Partner partner = findPartner(invoiceDto.getPartnerId());
		Serial serial = findSerial(invoiceDto.getSerialId());
		invoice.setPartner(partner);
		invoice.setSerial(serial);
		invoice.setTotal(invoiceDto.getTotal());
		invoice.setSubtotal(invoiceDto.getSubtotal());
		invoice.setTaxes(invoiceDto.getTaxes());
		invoice.setDate(invoiceDto.getDate());
		invoice.setUpdatedOn(System.currentTimeMillis());
		invoiceRepository.save(invoice);
	}

	private void computeInvoiceLines(Invoice invoice, ModifyInvoiceDto invoiceDto) {
		Set<InvoiceLine> lines = invoiceDto.getLines()
				.stream()
				.map(invoiceLineDto -> mapInvoiceLineDto(invoiceLineDto, invoice))
				.collect(Collectors.toSet());
		invoice.setLines(lines);
	}

	private InvoiceLine mapInvoiceLineDto(InvoiceLineDto invoiceLineDto, Invoice invoice) {
		InvoiceLine line = modelMapper.map(invoiceLineDto, InvoiceLine.class);
		if (invoiceLineDto.getId() == null) {
			line.setCreatedOn(System.currentTimeMillis());
		} else {
			InvoiceLine existingLine = findInvoiceLine(invoiceLineDto.getId(), invoice);
			line.setCreatedOn(existingLine.getCreatedOn());
		}
		line.setInvoice(invoice);
		line.setProduct(findProduct(invoiceLineDto.getProduct().getId()));
		line.setUpdatedOn(System.currentTimeMillis());
		return line;
	}

	private InvoiceLine findInvoiceLine(Long invoiceLineId, Invoice invoice) {
		return invoice.getLines()
				.stream()
				.filter(invoiceLine -> invoiceLine.getId().equals(invoiceLineId))
				.findFirst()
				.orElse(new InvoiceLine());
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

	private Invoice findInvoice(Long invoiceId) {
		return invoiceRepository
				.findById(invoiceId)
				.orElseThrow(InvoiceException::invoiceNotFound);
	}

	private Serial findSerial(Long serialId) {
		return serialRepository
				.findById(serialId)
				.orElseThrow(SerialException::serialNotFound);
	}

}
