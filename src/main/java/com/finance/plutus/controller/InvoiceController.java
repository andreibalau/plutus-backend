package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.InvoiceApi;
import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RestController
@RequiredArgsConstructor
public class InvoiceController implements InvoiceApi {

	private final FindInvoiceService findInvoiceService;

	public List<InvoiceDto> findAll() {
		return findInvoiceService.findAll();
	}

}
