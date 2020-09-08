package com.finance.plutus.controller;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.Valid;
import java.util.List;

import com.finance.plutus.controller.payload.CreateInvoiceRequest;
import com.finance.plutus.controller.payload.DownloadInvoiceRequest;
import com.finance.plutus.controller.payload.DownloadInvoicesRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindInvoiceResponse;
import com.finance.plutus.controller.payload.FindInvoicesResponse;
import com.finance.plutus.model.dto.InvoiceCommand;
import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.dto.PreviewInvoiceDto;
import com.finance.plutus.service.invoice.CreateInvoiceService;
import com.finance.plutus.service.invoice.DeleteInvoiceService;
import com.finance.plutus.service.invoice.DownloadInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.service.invoice.InvoiceCommandInvoker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoicesController {

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

  @PostMapping(
      value = "/{id}/command/{command}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void execute(@PathVariable Long id, @PathVariable InvoiceCommand command) {
    invoiceCommandInvoker.invoke(id, command);
  }

  @PostMapping(
      value = "/pdf/single",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> downloadSingle(
      @Valid @RequestBody DownloadInvoiceRequest request) {
    byte[] pdf = downloadInvoiceService.download(request.getInvoiceId());
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf");
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    ByteArrayResource resource = new ByteArrayResource(pdf);
    return ResponseEntity.ok()
        .headers(headers)
        .contentLength(pdf.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
  }

  @PostMapping(
      value = "/pdf/multiple",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> downloadMultiple(
      @Valid @RequestBody DownloadInvoicesRequest request) {
    byte[] zip = downloadInvoiceService.downloadAll(request.getInvoicesIds());
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archive.zip");
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    ByteArrayResource resource = new ByteArrayResource(zip);
    return ResponseEntity.ok()
        .headers(headers)
        .contentLength(zip.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
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
