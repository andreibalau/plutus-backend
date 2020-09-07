package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.CreateInvoiceRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindInvoiceResponse;
import com.finance.plutus.controller.payload.FindInvoicesResponse;
import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.dto.PreviewInvoiceDto;
import com.finance.plutus.service.invoice.CreateInvoiceService;
import com.finance.plutus.service.invoice.DeleteInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoicesController {

  private final DeleteInvoiceService deleteInvoiceService;
  private final FindInvoiceService findInvoiceService;
  private final CreateInvoiceService createInvoiceService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateInvoiceRequest request) {
    Long id = createInvoiceService.create(request.getInvoice());
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindInvoiceResponse findById(@PathVariable Long id) {
    InvoiceDto invoiceDto = findInvoiceService.findDtoById(id);
    return new FindInvoiceResponse(invoiceDto);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindInvoicesResponse findAllByPage(
      @RequestParam Integer page, @RequestParam Integer size) {
    List<PreviewInvoiceDto> invoices = findInvoiceService.findAllByPage(page, size);
    return new FindInvoicesResponse(invoices, page, findInvoiceService.countAll());
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable Long id) {
    deleteInvoiceService.delete(id);
  }
}
