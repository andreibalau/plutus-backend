package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.app.configuration.Api;
import com.finance.plutus.app.service.Params;
import com.finance.plutus.app.service.PdfGenerator;
import com.finance.plutus.app.service.Template;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.html.InvoiceParams;
import com.finance.plutus.invoice.service.InvoiceDownloader;
import com.finance.plutus.invoice.service.InvoiceFinder;
import com.finance.plutus.user.model.Business;
import com.finance.plutus.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceDownloaderImpl implements InvoiceDownloader {

  private final UserFinder userFinder;
  private final PdfGenerator pdfGenerator;
  private final InvoiceFinder invoiceFinder;

  @Override
  public byte[] download(Iterable<UUID> ids) {
    Business business = userFinder.getBusiness(Api.USER_EMAIL);
    List<Params<?>> params =
        invoiceFinder.findAllById(ids).stream()
            .map(invoice -> prepareParams(invoice, business))
            .collect(Collectors.toList());
    return pdfGenerator.generateMultiple(Template.INVOICE, params).orElseThrow();
  }

  @Override
  public byte[] downloadAll() {
    Business business = userFinder.getBusiness(Api.USER_EMAIL);
    List<Params<?>> params =
            invoiceFinder.findAll().stream()
                    .map(invoice -> prepareParams(invoice, business))
                    .collect(Collectors.toList());
    return pdfGenerator.generateMultiple(Template.INVOICE, params).orElseThrow();
  }

  private Params<Invoice> prepareParams(Invoice invoice, Business business) {
    InvoiceParams params = new InvoiceParams();
    params.submit(invoice);
    params.submitBusiness(business);
    return params;
  }
}
