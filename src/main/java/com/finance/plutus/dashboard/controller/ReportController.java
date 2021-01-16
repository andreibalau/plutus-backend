package com.finance.plutus.dashboard.controller;

import com.finance.plutus.invoice.service.InvoiceService;
import com.finance.plutus.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static com.finance.plutus.app.util.ResponseEntityUtils.prepareDownloadResponse;

/** Plutus Created by Catalin on 1/16/2021 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/reports")
public class ReportController {

  private final TransactionService transactionService;
  private final InvoiceService invoiceService;

  @GetMapping(
      value = "/transactions",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> prepareReport() {
    byte[] file = transactionService.prepareReport();
    return prepareDownloadResponse(file, "report.pdf");
  }

  @GetMapping(
      value = "/invoices",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> downloadArchive() {
    byte[] zip = invoiceService.downloadArchive();
    return prepareDownloadResponse(zip, "archive.zip");
  }
}
