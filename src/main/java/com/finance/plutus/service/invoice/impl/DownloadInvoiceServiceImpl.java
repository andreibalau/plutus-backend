package com.finance.plutus.service.invoice.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.model.pdf.Params;
import com.finance.plutus.model.pdf.Template;
import com.finance.plutus.service.invoice.DownloadInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.service.pdf.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class DownloadInvoiceServiceImpl implements DownloadInvoiceService {

  private final PdfGenerator pdfGenerator;
  private final FindInvoiceService findInvoiceService;

  @Override
  public byte[] download(Long id) {
    Invoice invoice = findInvoiceService.findById(id);
    Params params = prepareInvoiceParams(invoice);
    return pdfGenerator.generateSingle(Template.INVOICE, params).orElseThrow();
  }

  @Override
  public byte[] downloadAll(Iterable<Long> ids) {
    List<Params> paramsList =
        findInvoiceService.findAllByIds(ids).stream()
            .map(this::prepareInvoiceParams)
            .collect(Collectors.toList());
    return pdfGenerator.generateMultiple(Template.INVOICE, paramsList).orElseThrow();
  }

  private Params prepareInvoiceParams(Invoice invoice) {
    return Params.builder().name(invoice.getName()).build();
  }
}
