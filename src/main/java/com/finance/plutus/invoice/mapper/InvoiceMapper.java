package com.finance.plutus.invoice.mapper;

import com.finance.plutus.invoice.Invoice;
import com.finance.plutus.invoice.InvoiceStatus;
import com.finance.plutus.invoice.dto.CreateInvoiceDto;
import com.finance.plutus.invoice.dto.InvoiceDto;
import com.finance.plutus.partner.Partner;
import com.finance.plutus.partner.PartnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
@RequiredArgsConstructor
public class InvoiceMapper {

  private final PartnerMapper partnerMapper;
  private final InvoiceCurrencyMapper invoiceCurrencyMapper;
  private final InvoiceLineMapper invoiceLineMapper;

  public InvoiceDto mapToDto(Invoice invoice) {
    Partner partner = invoice.getClient();
    InvoiceDto invoiceDto = new InvoiceDto();
    invoiceDto.setId(invoice.getId().toString());
    invoiceDto.setCreatedOn(invoice.getCreatedOn());
    invoiceDto.setUpdatedOn(invoice.getUpdatedOn());
    invoiceDto.setDate(invoice.getDate());
    invoiceDto.setDueDate(invoice.getDueDate());
    invoiceDto.setName(invoice.getName());
    invoiceDto.setStatus(invoice.getStatus());
    invoiceDto.setPartner(partnerMapper.mapToDto(partner));
    invoiceDto.setSubtotal(invoice.getSubtotal());
    invoiceDto.setTaxes(invoice.getTaxes());
    invoiceDto.setTotal(invoice.getTotal());
    invoiceDto.setCurrency(
        Optional.ofNullable(invoice.getCurrency())
            .map(invoiceCurrencyMapper::mapToDto)
            .orElse(null));
    invoiceDto.setLines(
        invoice.getLines().stream().map(invoiceLineMapper::mapToDto).collect(Collectors.toList()));
    return invoiceDto;
  }

  public Invoice mapToEntity(CreateInvoiceDto createInvoiceDto) {
    Invoice invoice = new Invoice();
    invoice.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    invoice.setDate(createInvoiceDto.getDate());
    invoice.setDueDate(createInvoiceDto.getDueDate());
    invoice.setStatus(InvoiceStatus.DRAFT);
    invoice.setSubtotal(0D);
    invoice.setTaxes(0D);
    invoice.setTotal(0D);
    return invoice;
  }
}
