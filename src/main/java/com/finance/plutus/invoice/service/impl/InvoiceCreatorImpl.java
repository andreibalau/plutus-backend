package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.currency.model.Currency;
import com.finance.plutus.currency.model.CurrencyRate;
import com.finance.plutus.currency.service.CurrencyRateFinder;
import com.finance.plutus.invoice.model.CreateInvoiceDto;
import com.finance.plutus.invoice.model.CreateInvoiceLineDto;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceCurrency;
import com.finance.plutus.invoice.model.InvoiceLine;
import com.finance.plutus.invoice.model.InvoiceStatus;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.repository.InvoiceRepository;
import com.finance.plutus.invoice.service.InvoiceCreator;
import com.finance.plutus.invoice.service.SerialFinder;
import com.finance.plutus.invoice.service.SerialUpdater;
import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.model.ItemVat;
import com.finance.plutus.item.service.ItemFinder;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.service.PartnerFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class InvoiceCreatorImpl implements InvoiceCreator {

  private final ItemFinder itemFinder;
  private final SerialFinder serialFinder;
  private final PartnerFinder partnerFinder;
  private final SerialUpdater serialUpdater;
  private final InvoiceRepository invoiceRepository;
  private final CurrencyRateFinder currencyRateFinder;

  @Override
  @Transactional
  public UUID create(CreateInvoiceDto createInvoiceDto) {
    Invoice invoice = createInvoice(createInvoiceDto);
    Set<InvoiceLine> lines =
        createInvoiceDto.getLines().stream()
            .map(line -> createLine(line, invoice))
            .collect(Collectors.toSet());
    computeLines(lines, invoice);
    invoiceRepository.save(invoice);
    return invoice.getId();
  }

  private Invoice createInvoice(CreateInvoiceDto createInvoiceDto) {
    Partner partner = partnerFinder.findById(createInvoiceDto.getPartnerId());
    Serial serial = serialFinder.findById(createInvoiceDto.getSerialId());
    Invoice invoice = new Invoice();
    invoice.setName(serialUpdater.increment(serial));
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
    if (createInvoiceDto.getCurrency() != Currency.RON) {
      CurrencyRate currencyRate =
          currencyRateFinder.findLastByDateAndCurrency(
              createInvoiceDto.getDate(), createInvoiceDto.getCurrency());
      InvoiceCurrency invoiceCurrency = new InvoiceCurrency();
      invoiceCurrency.setValue(createInvoiceDto.getCurrency());
      invoiceCurrency.setRate(currencyRate.getRate());
      invoiceCurrency.setSubtotal(0D);
      invoiceCurrency.setTotal(0D);
      invoice.setCurrency(invoiceCurrency);
    }
    return invoiceRepository.save(invoice);
  }

  private InvoiceLine createLine(CreateInvoiceLineDto line, Invoice invoice) {
    Item item = itemFinder.findById(line.getItemId());
    int quantity = line.getQuantity();
    ItemVat vat = ItemVat.fromAmount(line.getVat());
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setInvoice(invoice);
    invoiceLine.setItem(item);
    invoiceLine.setUom(line.getUom());
    invoiceLine.setVat(vat);
    invoiceLine.setQuantity(quantity);
    double rate = 1;
    InvoiceCurrency invoiceCurrency = null;
    if (invoice.getCurrency() != null) {
      rate = invoice.getCurrency().getRate();
      invoiceCurrency = new InvoiceCurrency();
      invoiceCurrency.setValue(invoice.getCurrency().getValue());
    }
    double unitPrice = line.getUnitPrice() / rate;
    double subtotal = unitPrice * quantity;
    double total = vat.getAmount() * subtotal + subtotal;
    invoiceLine.setUnitPrice(unitPrice);
    invoiceLine.setSubtotal(subtotal);
    invoiceLine.setTotal(total);
    if (invoiceCurrency != null) {
      invoiceCurrency.setSubtotal(subtotal * rate);
      invoiceCurrency.setTotal(total * rate);
      invoiceLine.setCurrency(invoiceCurrency);
    }
    return invoiceLine;
  }

  private void computeLines(Set<InvoiceLine> lines, Invoice invoice) {
    double subtotal = lines.stream().mapToDouble(InvoiceLine::getSubtotal).sum();
    double total = lines.stream().mapToDouble(InvoiceLine::getTotal).sum();
    double taxes = total - subtotal;
    invoice.setSubtotal(subtotal);
    invoice.setTaxes(taxes);
    invoice.setTotal(total);
    if (invoice.getCurrency() != null) {
      double currencySubtotal =
          lines.stream()
              .map(InvoiceLine::getCurrency)
              .filter(Objects::nonNull)
              .mapToDouble(InvoiceCurrency::getSubtotal)
              .sum();
      double currencyTotal =
          lines.stream()
              .map(InvoiceLine::getCurrency)
              .filter(Objects::nonNull)
              .mapToDouble(InvoiceCurrency::getTotal)
              .sum();
      invoice.getCurrency().setSubtotal(currencySubtotal);
      invoice.getCurrency().setTotal(currencyTotal);
    }
    invoice.setLines(lines);
  }
}
