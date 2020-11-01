package com.finance.plutus.invoice.model;

import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.model.PartnerDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class InvoiceDto {
  private String id;
  private String name;
  private PartnerDto partner;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
  private LocalDate date;
  private LocalDate dueDate;
  private Double subtotal;
  private Double taxes;
  private Double total;
  private InvoiceStatus status;
  private List<InvoiceLineDto> lines;
  private InvoiceCurrencyDto currency;

  public static InvoiceDto mapFromEntity(Invoice invoice) {
    Partner partner = invoice.getClient();
    InvoiceDto invoiceDto = new InvoiceDto();
    invoiceDto.setId(invoice.getId().toString());
    invoiceDto.setCreatedOn(invoice.getCreatedOn());
    invoiceDto.setUpdatedOn(invoice.getUpdatedOn());
    invoiceDto.setDate(invoice.getDate());
    invoiceDto.setDueDate(invoice.getDueDate());
    invoiceDto.setName(invoice.getName());
    invoiceDto.setStatus(invoice.getStatus());
    invoiceDto.setPartner(PartnerDto.mapFromEntity(partner));
    invoiceDto.setSubtotal(invoice.getSubtotal());
    invoiceDto.setTaxes(invoice.getTaxes());
    invoiceDto.setTotal(invoice.getTotal());
    invoiceDto.setCurrency(
        Optional.ofNullable(invoice.getCurrency())
            .map(InvoiceCurrencyDto::mapFromEntity)
            .orElse(null));
    invoiceDto.setLines(
        invoice.getLines().stream()
            .map(InvoiceLineDto::mapFromEntity)
            .collect(Collectors.toList()));
    return invoiceDto;
  }
}
