package com.finance.plutus.invoice.controller;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.invoice.controller.payload.CreateInvoiceRequest;
import com.finance.plutus.invoice.controller.payload.FindInvoicesResponse;
import com.finance.plutus.invoice.service.InvoiceService;
import com.finance.plutus.invoice.model.InvoiceDto;
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

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

  private final InvoiceService invoiceService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateInvoiceRequest request) {
    UUID id = invoiceService.create(request.getInvoice());
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindInvoicesResponse findById(@PathVariable UUID id) {
    InvoiceDto invoice = invoiceService.findById(id);
    return FindInvoicesResponse.builder()
        .page(0)
        .size(1)
        .total(1)
        .invoices(List.of(invoice))
        .build();
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindInvoicesResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<InvoiceDto> invoices = invoiceService.findAll(page, size);
    long total = invoiceService.count();
    return FindInvoicesResponse.builder()
        .page(page)
        .size(size)
        .total(total)
        .invoices(invoices)
        .build();
  }

  @GetMapping(
      value = "/pdf/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> download(@PathVariable UUID id) {
    byte[] pdf = invoiceService.download(id);
    return prepareDownloadResponse(pdf, "invoice.pdf");
  }

  @GetMapping(
      value = "/pdf",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> download(@RequestParam List<UUID> ids) {
    byte[] zip = invoiceService.download(ids);
    return prepareDownloadResponse(zip, "archive.zip");
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    invoiceService.delete(id);
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
