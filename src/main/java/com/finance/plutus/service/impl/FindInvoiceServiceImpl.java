package com.finance.plutus.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.InvoiceDto;
import com.finance.plutus.model.dto.InvoiceLineDto;
import com.finance.plutus.model.dto.PreviewInvoiceDto;
import com.finance.plutus.model.dto.PreviewItemDto;
import com.finance.plutus.model.dto.PreviewPartnerDto;
import com.finance.plutus.model.entity.Invoice;
import com.finance.plutus.model.entity.InvoiceLine;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.InvoiceRepository;
import com.finance.plutus.service.FindInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/3/2020 */
@Service
@RequiredArgsConstructor
public class FindInvoiceServiceImpl implements FindInvoiceService {

  private final InvoiceRepository invoiceRepository;

  @Override
  public InvoiceDto findDtoById(Long id) {
    Invoice invoice = findById(id);
    return map(invoice);
  }

  @Override
  public Invoice findById(Long id) {
    return invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("invoice"));
  }

  @Override
  public List<PreviewInvoiceDto> findAllByPage(int page, int size) {
    return invoiceRepository.findAll(PageRequest.of(page, size)).stream()
        .map(i -> new PreviewInvoiceDto(i.getId(), i.getName(), i.getTotal()))
        .collect(Collectors.toList());
  }

  private InvoiceDto map(Invoice invoice) {
    Partner partner = invoice.getPartner();
    InvoiceDto invoiceDto = new InvoiceDto();
    invoiceDto.setId(invoice.getId());
    invoiceDto.setCreatedOn(invoice.getCreatedOn());
    invoiceDto.setUpdatedOn(invoice.getUpdatedOn());
    invoiceDto.setCurrency(invoice.getCurrency());
    invoiceDto.setDate(invoice.getDate());
    invoiceDto.setDueDate(invoice.getDueDate());
    invoiceDto.setName(invoice.getName());
    invoiceDto.setStatus(invoice.getStatus());
    invoiceDto.setPartner(
        new PreviewPartnerDto(partner.getId(), partner.getName(), partner.getEmail()));
    invoiceDto.setSubtotal(invoice.getSubtotal());
    invoiceDto.setTaxes(invoice.getTaxes());
    invoiceDto.setTotal(invoice.getTotal());
    invoiceDto.setType(invoice.getType());
    invoiceDto.setLines(
        invoice.getLines().stream().map(this::mapLine).collect(Collectors.toList()));
    return invoiceDto;
  }

  private InvoiceLineDto mapLine(InvoiceLine invoiceLine) {
    Item item = invoiceLine.getItem();
    InvoiceLineDto invoiceLineDto = new InvoiceLineDto();
    invoiceLineDto.setCreatedOn(invoiceLine.getCreatedOn());
    invoiceLineDto.setUpdatedOn(invoiceLine.getUpdatedOn());
    invoiceLineDto.setId(invoiceLine.getId());
    invoiceLineDto.setItem(new PreviewItemDto(item.getId(), item.getName()));
    invoiceLineDto.setPrice(invoiceLine.getPrice());
    invoiceLineDto.setQuantity(invoiceLine.getQuantity());
    invoiceLineDto.setSubtotal(invoiceLine.getSubtotal());
    invoiceLineDto.setVat(invoiceLine.getVat());
    invoiceLineDto.setTotal(invoiceLine.getTotal());
    invoiceLineDto.setUom(invoiceLine.getUom());
    return invoiceLineDto;
  }
}
