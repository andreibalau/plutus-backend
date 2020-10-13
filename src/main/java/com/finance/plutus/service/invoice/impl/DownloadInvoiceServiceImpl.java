package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.model.pdf.Params;
import com.finance.plutus.model.pdf.Template;
import com.finance.plutus.service.invoice.DownloadInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.service.pdf.PdfGenerator;
import com.finance.plutus.service.user.FindBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class DownloadInvoiceServiceImpl implements DownloadInvoiceService {

  private final PdfGenerator pdfGenerator;
  private final FindInvoiceService findInvoiceService;
  private final FindBusinessService findBusinessService;

  @Override
  public byte[] download(UUID id) {
    Invoice invoice = findInvoiceService.findEntityById(id);
    Business business = findBusinessService.getEntity();
    return pdfGenerator
        .generateSingle(Template.INVOICE, Params.set(invoice, business))
        .orElseThrow();
  }

  @Override
  public byte[] downloadAll(Iterable<UUID> ids) {
    Business business = findBusinessService.getEntity();
    List<Params> paramsList =
        findInvoiceService.findAllEntities(ids).stream()
            .map(invoice -> Params.set(invoice, business))
            .collect(Collectors.toList());
    return pdfGenerator.generateMultiple(Template.INVOICE, paramsList).orElseThrow();
  }
}
