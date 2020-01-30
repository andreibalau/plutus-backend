package com.finance.plutus.service.invoice.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.finance.plutus.exception.InvoiceException;
import com.finance.plutus.exception.PartnerException;
import com.finance.plutus.exception.ProductException;
import com.finance.plutus.exception.SerialException;
import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.model.invoice.InvoiceLine;
import com.finance.plutus.model.invoice.Status;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceLineDto;
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

	private void makeLinesComputations(Invoice invoice, ModifyInvoiceDto invoiceDto) {
		List<ModifyInvoiceLineDto> invoiceLineDtos = invoiceDto.getLines();
		Set<InvoiceLine> invoiceLineSet = new HashSet<>();
		invoiceLineDtos.forEach(invoiceLineDto -> {
			double quantity = invoiceLineDto.getQuantity();
			double price = invoiceLineDto.getPrice();
			double taxes = invoiceLineDto.getTaxes();
			double subtotal = quantity * price;
			double total = subtotal + taxes;
			InvoiceLine line = modelMapper.map(invoiceLineDto, InvoiceLine.class);
			line.setTotal(total);
			line.setSubtotal(subtotal);
			line.setProduct(findProduct(invoiceLineDto.getProductId()));
			invoiceLineSet.add(line);
			invoice.setSubtotal(invoice.getSubtotal() + subtotal);
			invoice.setTotal(invoice.getTotal() + total);
			invoice.setTaxes(invoice.getTaxes() + taxes);
		});
		invoice.setLines(invoiceLineSet);
	}

	private void updateInvoice(Invoice invoice, ModifyInvoiceDto invoiceDto) {
		makeLinesComputations(invoice, invoiceDto);
		Partner vendor = findPartner(invoiceDto.getVendorId());
		Partner client = findPartner(invoiceDto.getClientId());
		Serial serial = findSerial(invoiceDto.getSerialId());
		invoice.setVendor(vendor);
		invoice.setClient(client);
		invoice.setSerial(serial);
		invoice.setUpdatedOn(System.currentTimeMillis());
		invoiceRepository.save(invoice);
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
