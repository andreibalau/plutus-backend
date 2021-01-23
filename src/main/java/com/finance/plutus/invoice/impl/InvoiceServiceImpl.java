package com.finance.plutus.invoice.impl;

import com.finance.plutus.app.configuration.Api;
import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.app.service.Params;
import com.finance.plutus.app.service.PdfGenerator;
import com.finance.plutus.app.service.Template;
import com.finance.plutus.currency.Currency;
import com.finance.plutus.currency.CurrencyRate;
import com.finance.plutus.currency.CurrencyRateService;
import com.finance.plutus.invoice.Invoice;
import com.finance.plutus.invoice.InvoiceCurrency;
import com.finance.plutus.invoice.InvoiceLine;
import com.finance.plutus.invoice.InvoiceRepository;
import com.finance.plutus.invoice.InvoiceService;
import com.finance.plutus.invoice.InvoiceStatus;
import com.finance.plutus.invoice.dto.CreateInvoiceDto;
import com.finance.plutus.invoice.dto.CreateInvoiceLineDto;
import com.finance.plutus.invoice.dto.html.InvoiceParams;
import com.finance.plutus.invoice.exception.WrongInvoiceStatusException;
import com.finance.plutus.invoice.mapper.InvoiceMapper;
import com.finance.plutus.item.Item;
import com.finance.plutus.item.ItemService;
import com.finance.plutus.item.ItemVat;
import com.finance.plutus.transaction.TransactionService;
import com.finance.plutus.transaction.dto.CreateTransactionDto;
import com.finance.plutus.transaction.model.TransactionMethod;
import com.finance.plutus.transaction.model.TransactionType;
import com.finance.plutus.user.Business;
import com.finance.plutus.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceMapper invoiceMapper;
  private final InvoiceRepository invoiceRepository;
  private final CurrencyRateService currencyRateService;
  private final ItemService itemService;
  private final TransactionService transactionService;
  private final UserService userService;
  private final PdfGenerator pdfGenerator;

  @Override
  @Transactional
  public void delete(UUID id) {
    Invoice invoice = findById(id);
    if (invoice.getStatus() == InvoiceStatus.DONE) {
      throw new WrongInvoiceStatusException();
    }
    invoiceRepository.delete(invoice);
  }

  @Override
  @Transactional
  public UUID create(CreateInvoiceDto createInvoiceDto) {
    Invoice invoice = invoiceMapper.mapToEntity(createInvoiceDto);
    if (createInvoiceDto.getCurrency() != Currency.RON) {
      CurrencyRate currencyRate =
          currencyRateService.findLastByDateAndCurrency(
              createInvoiceDto.getDate(), createInvoiceDto.getCurrency());
      InvoiceCurrency invoiceCurrency = new InvoiceCurrency();
      invoiceCurrency.setValue(createInvoiceDto.getCurrency());
      invoiceCurrency.setRate(currencyRate.getRate());
      invoiceCurrency.setSubtotal(0D);
      invoiceCurrency.setTotal(0D);
      invoice.setCurrency(invoiceCurrency);
    }
    Set<InvoiceLine> invoiceLines =
        createInvoiceDto.getLines().stream()
            .map(createInvoiceLineDto -> createLine(createInvoiceLineDto, invoice.getCurrency()))
            .collect(Collectors.toSet());
    computeLines(invoiceLines, invoice);
    invoiceRepository.save(invoice);
    return invoice.getId();
  }

  @Override
  public void collect(Iterable<UUID> ids) {
    ids.forEach(this::collect);
  }

  @Override
  @Transactional
  public void collect(UUID id) {
    Invoice invoice = findById(id);
    if (invoice.getStatus() != InvoiceStatus.DONE) {
      invoice.setStatus(InvoiceStatus.DONE);
      invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      invoiceRepository.save(invoice);
      createTransaction(invoice);
    }
  }

  @Override
  public Invoice findById(UUID id) {
    return invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("invoice"));
  }

  @Override
  public List<Invoice> findAllById(Iterable<UUID> ids) {
    return invoiceRepository.findAllById(ids);
  }

  @Override
  public List<Invoice> findAll(int page, int size) {
    return invoiceRepository.findAll(PageRequest.of(page, size)).toList();
  }

  @Override
  public List<Invoice> findAll() {
    return invoiceRepository.findAll();
  }

  @Override
  public byte[] download(UUID id) {
    Business business = userService.getBusiness(Api.USER_EMAIL);
    Invoice invoice = findById(id);
    Params<?> params = prepareParams(invoice, business);
    return pdfGenerator.generateSingle(Template.INVOICE, params).orElseThrow();
  }

  @Override
  public byte[] downloadAll() {
    Business business = userService.getBusiness(Api.USER_EMAIL);
    List<Params<?>> params =
        findAll().stream()
            .map(invoice -> prepareParams(invoice, business))
            .collect(Collectors.toList());
    return pdfGenerator.generateMultiple(Template.INVOICE, params).orElseThrow();
  }

  private InvoiceLine createLine(
      CreateInvoiceLineDto createInvoiceLineDto, InvoiceCurrency invoiceCurrency) {
    Item item = itemService.findById(createInvoiceLineDto.getItemId());
    int quantity = createInvoiceLineDto.getQuantity();
    ItemVat vat = ItemVat.fromAmount(createInvoiceLineDto.getVat());
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoiceLine.setUom(createInvoiceLineDto.getUom());
    invoiceLine.setItem(item);
    invoiceLine.setVat(vat);
    invoiceLine.setQuantity(quantity);
    double rate = 1;
    InvoiceCurrency invoiceLineCurrency = null;
    if (invoiceCurrency != null) {
      rate = invoiceCurrency.getRate();
      invoiceLineCurrency = new InvoiceCurrency();
      invoiceLineCurrency.setRate(rate);
      invoiceLineCurrency.setValue(invoiceCurrency.getValue());
    }
    double unitPrice = createInvoiceLineDto.getUnitPrice() * rate;
    double subtotal = unitPrice * quantity;
    double total = vat.getAmount() * subtotal + subtotal;
    invoiceLine.setUnitPrice(unitPrice);
    invoiceLine.setSubtotal(subtotal);
    invoiceLine.setTotal(total);
    if (invoiceLineCurrency != null) {
      invoiceLineCurrency.setSubtotal(subtotal / rate);
      invoiceLineCurrency.setTotal(total / rate);
      invoiceLine.setCurrency(invoiceLineCurrency);
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
    lines.forEach(line -> line.setInvoice(invoice));
  }

  private void createTransaction(Invoice invoice) {
    CreateTransactionDto createTransactionDto = new CreateTransactionDto();
    createTransactionDto.setDate(invoice.getDate());
    createTransactionDto.setMethod(TransactionMethod.BANK);
    createTransactionDto.setType(TransactionType.INCOME);
    createTransactionDto.setPartnerId(invoice.getClient().getId());
    createTransactionDto.setValue(invoice.getTotal());
    createTransactionDto.setDocument(String.format("Factura %s", invoice.getName()));
    createTransactionDto.setDetails("Incasare client Upwork");
    createTransactionDto.setDeductible(false);
    transactionService.create(createTransactionDto);
  }

  private Params<Invoice> prepareParams(Invoice invoice, Business business) {
    InvoiceParams params = new InvoiceParams();
    params.submit(invoice);
    params.submitBusiness(business);
    return params;
  }
}
