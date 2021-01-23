package com.finance.plutus.dashboard;

import com.finance.plutus.invoice.InvoiceFacadeService;
import com.finance.plutus.transaction.TransactionFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 1/16/2021 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/reports")
public class ReportController {

  private final TransactionFacadeService transactionFacadeService;
  private final InvoiceFacadeService invoiceFacadeService;

  @GetMapping(
      value = "/transactions",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> prepareReport() {
    return transactionFacadeService.prepareReport();
  }

  @GetMapping(
      value = "/invoices",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ResponseEntity<Resource> downloadArchive() {
    return invoiceFacadeService.downloadArchive();
  }
}
