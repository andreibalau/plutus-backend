package com.finance.plutus.controller.invoice;

import java.util.List;

import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.util.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping(Api.INVOICES)
@RestController
public class InvoiceController {

	private final FindInvoiceService findInvoiceService;

	@GetMapping
	public List<InvoiceDto> findAll() {
		return findInvoiceService.findAll();
	}

}
