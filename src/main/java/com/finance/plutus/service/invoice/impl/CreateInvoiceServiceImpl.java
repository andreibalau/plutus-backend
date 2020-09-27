package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.model.dto.CreateInvoiceDto;
import com.finance.plutus.model.dto.CreateInvoiceLineDto;
import com.finance.plutus.model.entity.*;
import com.finance.plutus.repository.InvoiceRepository;
import com.finance.plutus.service.invoice.CreateInvoiceService;
import com.finance.plutus.service.item.FindItemService;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  private final FindSerialService findSerialService;

  @Override
  @Transactional
  public String create(CreateInvoiceDto createInvoiceDto) {
    Invoice invoice = createInvoice(createInvoiceDto);
    invoiceRepository.save(invoice);
    Set<InvoiceLine> lines =
        createInvoiceDto.getLines().stream()
            .map(line -> createLine(line, invoice))
            .collect(Collectors.toSet());
    computeLines(lines, invoice);
    invoiceRepository.save(invoice);
    return invoice.getId();
  }

  private Invoice createInvoice(CreateInvoiceDto createInvoiceDto) {
    Partner partner = findPartnerService.findEntityById(createInvoiceDto.getPartnerId());
    Serial serial = findSerialService.findEntityById(createInvoiceDto.getSerialId());
    Invoice invoice = new Invoice();
    invoice.setName("DRAFT");
    invoice.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setCurrency(createInvoiceDto.getCurrency());
    invoice.setDate(createInvoiceDto.getDate());
    invoice.setDueDate(createInvoiceDto.getDueDate());
    invoice.setStatus(InvoiceStatus.DRAFT);
    invoice.setSubtotal(0D);
    invoice.setTaxes(0D);
    invoice.setTotal(0D);
    invoice.setClient(partner);
    invoice.setSerial(serial);
    return invoice;
  }

  private InvoiceLine createLine(CreateInvoiceLineDto line, Invoice invoice) {
    Item item = findItemService.findEntityById(line.getItemId());
    double unitPrice = line.getUnitPrice();
    ItemVat vat = ItemVat.fromAmount(line.getVat());
    int quantity = line.getQuantity();
    double subtotal = unitPrice * quantity;
    double total = vat.getAmount() * subtotal + subtotal;
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setInvoice(invoice);
    invoiceLine.setItem(item);
    invoiceLine.setUnitPrice(unitPrice);
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
  }
}
