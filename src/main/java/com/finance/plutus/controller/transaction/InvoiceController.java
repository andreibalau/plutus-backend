package com.finance.plutus.controller.transaction;

import com.finance.plutus.model.transaction.dto.InvoiceDto;
import com.finance.plutus.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
@RestController
public class InvoiceController {

	private final InvoiceService invoiceService;

	@GetMapping
	public List<InvoiceDto> findAll() {
		return invoiceService.findAll();
	}

}
