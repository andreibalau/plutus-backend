package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.model.dto.InvoiceHtmlDto;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.model.html.Params;
import com.finance.plutus.model.html.Template;
import com.finance.plutus.service.invoice.DownloadInvoiceService;
import com.finance.plutus.service.invoice.FindInvoiceService;
import com.finance.plutus.service.user.FindBusinessService;
import com.finance.plutus.util.HtmlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class DownloadInvoiceServiceImpl implements DownloadInvoiceService {

  private final FindInvoiceService findInvoiceService;
  private final FindBusinessService findBusinessService;

  @Override
  public InvoiceHtmlDto download(UUID id) {
    Business business = findBusinessService.getEntity();
    Invoice invoice = findInvoiceService.findEntityById(id);
    return createContent(invoice, business);
  }

  @Override
  public List<InvoiceHtmlDto> download(Iterable<UUID> ids) {
    Business business = findBusinessService.getEntity();
    return findInvoiceService.findAllEntities(ids).stream()
        .map(invoice -> createContent(invoice, business))
        .collect(Collectors.toList());
  }

  private InvoiceHtmlDto createContent(Invoice invoice, Business business) {
    String html = HtmlUtils.parseTemplate(Template.INVOICE_NEW, Params.set(invoice, business));
    return new InvoiceHtmlDto(invoice.getName(), html);
  }

  //  @Override
  //  public byte[] download(UUID id) {
  //    Invoice invoice = findInvoiceService.findEntityById(id);
  //    Business business = findBusinessService.getEntity();
  //    return pdfGenerator
  //        .generateSingle(Template.INVOICE, Params.set(invoice, business))
  //        .orElseThrow();
  //  }
  //
  //  @Override
  //  public byte[] downloadAll(Iterable<UUID> ids) {
  //    Business business = findBusinessService.getEntity();
  //    List<Params> paramsList =
  //        findInvoiceService.findAllEntities(ids).stream()
  //            .map(invoice -> Params.set(invoice, business))
  //            .collect(Collectors.toList());
  //    return pdfGenerator.generateMultiple(Template.INVOICE, paramsList).orElseThrow();
  //  }
}
