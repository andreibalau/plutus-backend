package com.finance.plutus.invoice.impl;

import com.finance.plutus.app.configuration.Api;
import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.app.service.Params;
import com.finance.plutus.app.service.PdfGenerator;
import com.finance.plutus.app.service.Template;
import com.finance.plutus.currency.CurrencyRate;
import com.finance.plutus.currency.CurrencyRateService;
import com.finance.plutus.invoice.*;
import com.finance.plutus.invoice.dto.CreateInvoiceDto;
import com.finance.plutus.invoice.dto.CreateInvoiceLineDto;
import com.finance.plutus.invoice.dto.UpdateInvoiceDto;
import com.finance.plutus.invoice.dto.UpdateInvoiceLineDto;
import com.finance.plutus.invoice.dto.html.InvoiceParams;
import com.finance.plutus.invoice.exception.WrongInvoiceStatusException;
import com.finance.plutus.invoice.mapper.InvoiceLineMapper;
import com.finance.plutus.invoice.mapper.InvoiceMapper;
import com.finance.plutus.item.Item;
import com.finance.plutus.item.ItemService;
import com.finance.plutus.partner.Partner;
import com.finance.plutus.partner.PartnerService;
import com.finance.plutus.serial.Serial;
import com.finance.plutus.serial.SerialService;
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
  private final InvoiceLineMapper invoiceLineMapper;
  private final InvoiceRepository invoiceRepository;
  private final CurrencyRateService currencyRateService;
  private final ItemService itemService;
  private final TransactionService transactionService;
  private final PartnerService partnerService;
  private final SerialService serialService;
  private final UserService userService;
  private final PdfGenerator pdfGenerator;

  @Override
  @Transactional
  public UUID create(CreateInvoiceDto createInvoiceDto) {
    Invoice invoice = invoiceMapper.mapToEntity(createInvoiceDto);
    Partner partner = partnerService.findById(createInvoiceDto.getPartnerId());
    invoice.setCustomer(partner);
    Serial serial = serialService.findById(createInvoiceDto.getSerialId());
    invoice.setName(serialService.increment(serial));
    invoice.setSerial(serial);
    updateCurrency(invoice);
    Set<InvoiceLine> invoiceLines =
        createInvoiceDto.getLines().stream()
            .map(createInvoiceLineDto -> createInvoiceLine(invoice, createInvoiceLineDto))
            .collect(Collectors.toSet());
    computeLines(invoiceLines, invoice);
    invoiceRepository.save(invoice);
    return invoice.getId();
  }

  @Override
  @Transactional
  public void update(UUID id, UpdateInvoiceDto updateInvoiceDto) {
    Invoice invoice = findById(id);
    Partner partner = partnerService.findById(updateInvoiceDto.getPartnerId());
    invoice.setCustomer(partner);
    Invoice updatedInvoice = invoiceMapper.mapToEntity(invoice, updateInvoiceDto);
    updateCurrency(updatedInvoice);
    Set<InvoiceLine> invoiceLines =
        updateInvoiceDto.getLines().stream()
            .map(updateInvoiceLineDto -> createInvoiceLine(updatedInvoice, updateInvoiceLineDto))
            .collect(Collectors.toSet());
    computeLines(invoiceLines, updatedInvoice);
    invoiceRepository.save(updatedInvoice);
  }

  @Override
  @Transactional
  public void collect(Iterable<UUID> ids) {
    findAllById(ids).forEach(this::collect);
  }

  @Override
  @Transactional
  public void collect(UUID id) {
    Invoice invoice = findById(id);
    collect(invoice);
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
  @Transactional
  public void delete(UUID id) {
    Invoice invoice = findById(id);
    if (invoice.getStatus() == InvoiceStatus.DONE) {
      throw new WrongInvoiceStatusException();
    }
    invoiceRepository.delete(invoice);
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

  private void collect(Invoice invoice) {
    if (invoice.getStatus() != InvoiceStatus.DONE) {
      invoice.setStatus(InvoiceStatus.DONE);
      invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      invoiceRepository.save(invoice);
      createTransaction(invoice);
    }
  }

  private void createTransaction(Invoice invoice) {
    CreateTransactionDto createTransactionDto = new CreateTransactionDto();
    createTransactionDto.setDate(invoice.getDate());
    createTransactionDto.setMethod(TransactionMethod.BANK);
    createTransactionDto.setType(TransactionType.INCOME);
    createTransactionDto.setPartnerId(invoice.getCustomer().getId());
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

  private void updateCurrency(Invoice invoice) {
    if (invoice.getCurrency() != null) {
      CurrencyRate currencyRate =
          currencyRateService.findLastByDateAndCurrency(
              invoice.getDate(), invoice.getCurrency().getValue());
      invoice.getCurrency().setRate(currencyRate.getRate());
    }
  }

  private InvoiceLine createInvoiceLine(
      Invoice invoice, CreateInvoiceLineDto createInvoiceLineDto) {
    Item item = itemService.findById(createInvoiceLineDto.getItemId());
    InvoiceLine invoiceLine =
        invoiceLineMapper.mapToEntity(createInvoiceLineDto, invoice.getCurrency());
    invoiceLine.setItem(item);
    return invoiceLine;
  }

  private InvoiceLine createInvoiceLine(
      Invoice invoice, UpdateInvoiceLineDto updateInvoiceLineDto) {
    Item item = itemService.findById(updateInvoiceLineDto.getItemId());
    InvoiceLine invoiceLine =
        invoiceLineMapper.mapToEntity(updateInvoiceLineDto, invoice.getCurrency());
    invoiceLine.setItem(item);
    return invoiceLine;
  }

  private void computeLines(Set<InvoiceLine> invoiceLines, Invoice invoice) {
    double subtotal = invoiceLines.stream().mapToDouble(InvoiceLine::getSubtotal).sum();
    double total = invoiceLines.stream().mapToDouble(InvoiceLine::getTotal).sum();
    double taxes = total - subtotal;
    invoice.setSubtotal(subtotal);
    invoice.setTaxes(taxes);
    invoice.setTotal(total);
    if (invoice.getCurrency() != null) {
      double currencySubtotal =
          invoiceLines.stream()
              .map(InvoiceLine::getCurrency)
              .filter(Objects::nonNull)
              .mapToDouble(InvoiceCurrency::getSubtotal)
              .sum();
      double currencyTotal =
          invoiceLines.stream()
              .map(InvoiceLine::getCurrency)
              .filter(Objects::nonNull)
              .mapToDouble(InvoiceCurrency::getTotal)
              .sum();
      invoice.getCurrency().setSubtotal(currencySubtotal);
      invoice.getCurrency().setTotal(currencyTotal);
    }
    invoice.setLines(invoiceLines);
    invoiceLines.forEach(line -> line.setInvoice(invoice));
  }
}
