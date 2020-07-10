package com.finance.plutus.service.impl;

import com.finance.plutus.model.dto.CreateInvoiceDto;
import com.finance.plutus.model.dto.CreateInvoiceLineDto;
import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.model.entity.InvoiceLine;
import com.finance.plutus.model.entity.InvoiceStatus;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.InvoiceRepository;
import com.finance.plutus.service.CreateInvoiceService;
import com.finance.plutus.service.FindItemService;
import com.finance.plutus.service.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/3/2020 */
@Service
@RequiredArgsConstructor
public class CreateInvoiceServiceImpl implements CreateInvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final FindPartnerService findPartnerService;
  private final FindItemService findItemService;

  @Override
  public Long create(CreateInvoiceDto createInvoiceDto) {
    Invoice invoice = createInvoice(createInvoiceDto);
    invoiceRepository.save(invoice);
    Set<InvoiceLine> lines =
        createInvoiceDto.getLines().stream()
            .map(line -> createLine(line, invoice))
            .collect(Collectors.toSet());
    computeLines(lines, invoice);
    return invoice.getId();
  }

  private Invoice createInvoice(CreateInvoiceDto createInvoiceDto) {
    Partner partner = findPartnerService.findById(createInvoiceDto.getPartner());
    Invoice invoice = new Invoice();
    invoice.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setCurrency(createInvoiceDto.getCurrency());
    invoice.setDate(createInvoiceDto.getDate());
    invoice.setDueDate(createInvoiceDto.getDueDate());
    invoice.setStatus(InvoiceStatus.DRAFT);
    invoice.setType(createInvoiceDto.getType());
    invoice.setPartner(partner);
    double subtotal = 0;
    double taxes = 0;
    double total = 0;
    invoice.setSubtotal(subtotal);
    invoice.setTaxes(taxes);
    invoice.setTotal(total);
    return invoice;
  }

  private InvoiceLine createLine(CreateInvoiceLineDto line, Invoice invoice) {
    Item item = findItemService.findById(line.getItem());
    double unitPrice = line.getPrice();
    double vat = line.getVat();
    int quantity = line.getQuantity();
    double subtotal = unitPrice * quantity;
    double total = vat * subtotal + subtotal;
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setInvoice(invoice);
    invoiceLine.setItem(item);
    invoiceLine.setPrice(unitPrice);
    invoiceLine.setQuantity(quantity);
    invoiceLine.setSubtotal(subtotal);
    invoiceLine.setVat(vat);
    invoiceLine.setTotal(total);
    invoiceLine.setUom(line.getUom());
    return invoiceLine;
  }

  private void computeLines(Set<InvoiceLine> lines, Invoice invoice) {
    double subtotal = lines.stream().mapToDouble(InvoiceLine::getSubtotal).sum();
    double total = lines.stream().mapToDouble(InvoiceLine::getTotal).sum();
    double taxes = total - subtotal;
    invoice.setSubtotal(subtotal);
    invoice.setTaxes(taxes);
    invoice.setTotal(total);
    invoice.setLines(lines);
    invoiceRepository.save(invoice);
  }
}
