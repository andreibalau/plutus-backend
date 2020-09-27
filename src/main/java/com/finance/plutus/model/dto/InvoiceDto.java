package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class InvoiceDto {
  private Long id;
  private String name;
  private PartnerDto partner;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
  private LocalDate date;
  private LocalDate dueDate;
  private Double subtotal;
  private Double taxes;
  private Double total;
  private InvoiceType type;
  private InvoiceStatus status;
  private Currency currency;
  private List<InvoiceLineDto> lines;

  public static InvoiceDto mapFromEntity(Invoice invoice) {
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
    invoiceDto.setPartner(PartnerDto.mapFromEntity(partner));
    invoiceDto.setSubtotal(invoice.getSubtotal());
    invoiceDto.setTaxes(invoice.getTaxes());
    invoiceDto.setTotal(invoice.getTotal());
    invoiceDto.setType(invoice.getType());
    invoiceDto.setLines(
        invoice.getLines().stream()
            .map(InvoiceLineDto::mapFromEntity)
            .collect(Collectors.toList()));
    return invoiceDto;
  }
}
