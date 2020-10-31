package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.currency.model.Currency;
import com.finance.plutus.currency.model.CurrencyRate;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceLine;
import com.finance.plutus.invoice.model.InvoiceStatus;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.repository.InvoiceRepository;
import com.finance.plutus.invoice.service.InvoiceCreator;
import com.finance.plutus.old.model.dto.CreateInvoiceDto;
import com.finance.plutus.old.model.dto.CreateInvoiceLineDto;
import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.model.ItemVat;
import com.finance.plutus.partner.model.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceCreatorImpl implements InvoiceCreator {

  private final InvoiceRepository invoiceRepository;

  @Override
  @Transactional
  public UUID create(CreateInvoiceDto createInvoiceDto) {
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
    invoice.setName(increaseSerialService.getDraftName(serial));
    invoice.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setDate(createInvoiceDto.getDate());
    invoice.setDueDate(createInvoiceDto.getDueDate());
    invoice.setStatus(InvoiceStatus.DRAFT);
    invoice.setSubtotal(0D);
    invoice.setTaxes(0D);
    invoice.setTotal(0D);
    invoice.setClient(partner);
    invoice.setSerial(serial);
    if (createInvoiceDto.getCurrency() == Currency.RON) {
      invoice.setCurrencyRate(1.00);
    } else {
      CurrencyRate currencyRate =
          currencyService.findLastRateByDate(
              createInvoiceDto.getDate(), createInvoiceDto.getCurrency());
      invoice.setCurrencyRate(currencyRate.getRate());
    }
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
    invoiceLine.setCurrency(invoice.getCurrency());
    invoiceLine.setUom(line.getUom());
    invoiceLine.setVat(vat);
    invoiceLine.setQuantity(quantity);
    invoiceLine.setUnitPrice(unitPrice * invoice.getCurrencyRate());
    invoiceLine.setSubtotal(subtotal * invoice.getCurrencyRate());
    invoiceLine.setTotal(total * invoice.getCurrencyRate());
    return invoiceLine;
  }

  private void computeLines(Set<InvoiceLine> lines, Invoice invoice) {
    double subtotal = lines.stream().mapToDouble(InvoiceLine::getSubtotal).sum();
    double total = lines.stream().mapToDouble(InvoiceLine::getTotal).sum();
    double taxes = total - subtotal;
    double currencySubtotal = lines.stream().mapToDouble(InvoiceLine::getCurrencySubtotal).sum();
    double currencyTotal = lines.stream().mapToDouble(InvoiceLine::getCurrencyTotal).sum();
    double currencyTaxes = currencyTotal - currencySubtotal;
    invoice.setSubtotal(subtotal);
    invoice.setTaxes(taxes);
    invoice.setTotal(total);
    invoice.setLines(lines);
  }
}
