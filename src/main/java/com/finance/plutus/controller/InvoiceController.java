package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.*;
import com.finance.plutus.model.dto.InvoiceCommand;
import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.dto.InvoiceHtmlDto;
import com.finance.plutus.service.invoice.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

  private final DeleteInvoiceService deleteInvoiceService;
  private final FindInvoiceService findInvoiceService;
  private final CreateInvoiceService createInvoiceService;
  private final InvoiceCommandInvoker invoiceCommandInvoker;
  private final DownloadInvoiceService downloadInvoiceService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateInvoiceRequest request) {
    UUID id = createInvoiceService.create(request.getInvoice());
    invoiceCommandInvoker.invoke(id, InvoiceCommand.ACTIVATE);
    invoiceCommandInvoker.invoke(id, InvoiceCommand.COMPLETE);
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindInvoiceResponse findById(@PathVariable UUID id) {
    InvoiceDto invoiceDto = findInvoiceService.findDtoById(id);
    return new FindInvoiceResponse(invoiceDto);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindInvoicesResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<InvoiceDto> invoices = findInvoiceService.findAllDtoByPage(page, size);
    return new FindInvoicesResponse(invoices, page, size, findInvoiceService.countAll());
  }

  @GetMapping(
      value = "/html/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public DownloadInvoiceResponse downloadHtml(@PathVariable UUID id) {
    InvoiceHtmlDto invoice = downloadInvoiceService.downloadHtml(id);
    return new DownloadInvoiceResponse(invoice);
  }

  @GetMapping(
      value = "/html",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public DownloadInvoicesResponse downloadHtml(@RequestParam List<UUID> ids) {
    List<InvoiceHtmlDto> invoices = downloadInvoiceService.downloadHtml(ids);
    return new DownloadInvoicesResponse(invoices);
  }

  @GetMapping(
          value = "/pdf/{id}",
          consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
          produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> downloadPdf(@PathVariable UUID id) {
    byte[] pdf = downloadInvoiceService.download(id);
    return prepareDownloadResponse(pdf, "invoice.pdf");
  }

  @GetMapping(
          value = "/pdf",
          consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
          produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> downloadPdf(@RequestParam List<UUID> ids) {
    byte[] zip = downloadInvoiceService.download(ids);
    return prepareDownloadResponse(zip, "archive.zip");
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    deleteInvoiceService.delete(id);
  }

  private ResponseEntity<Resource> prepareDownloadResponse(byte[] content, String filename) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    ByteArrayResource resource = new ByteArrayResource(content);
    return ResponseEntity.ok()
            .headers(headers)
            .contentLength(content.length)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
  }
}
