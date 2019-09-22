package com.finance.plutus.controller.invoice;

import com.finance.plutus.model.invoice.dto.InvoiceDto;
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
@RequestMapping("/api/invoices")
@RestController
public class InvoiceController {

	private final InvoiceService invoiceService;

	@GetMapping("/")
	public List<InvoiceDto> findAll() {
		return invoiceService.findAll();
	}

}
