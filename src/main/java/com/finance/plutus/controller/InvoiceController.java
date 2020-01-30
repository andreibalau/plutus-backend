package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.InvoiceApi;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.invoice.dto.ChangeStatusDto;
import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;
import com.finance.plutus.model.invoice.dto.PreviewInvoiceDto;
import com.finance.plutus.service.invoice.ChangeInvoiceStatusService;
import com.finance.plutus.service.invoice.CreateInvoiceService;
import com.finance.plutus.service.invoice.DeleteInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.service.invoice.UpdateInvoiceService;
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
	private final CreateInvoiceService createInvoiceService;
	private final UpdateInvoiceService updateInvoiceService;
	private final DeleteInvoiceService deleteInvoiceService;
	private final ChangeInvoiceStatusService changeInvoiceStatusService;

	@Override
	public List<PreviewInvoiceDto> findAll() {
		return findInvoiceService.findAll();
	}

	@Override
	public EntityCreatedDto create(ModifyInvoiceDto modifyInvoiceDto) {
		return createInvoiceService.create(modifyInvoiceDto);
	}

	@Override
	public InvoiceDto findById(Long invoiceId) {
		return findInvoiceService.findById(invoiceId);
	}

	@Override
	public void update(Long invoiceId, ModifyInvoiceDto modifyInvoiceDto) {
		updateInvoiceService.update(invoiceId, modifyInvoiceDto);
	}

	@Override
	public void delete(Long invoiceId) {
		deleteInvoiceService.delete(invoiceId);
	}

	@Override
	public void changeStatus(Long invoiceId, ChangeStatusDto changeStatusDto) {
		changeInvoiceStatusService.changeStatus(invoiceId, changeStatusDto);
	}

}
