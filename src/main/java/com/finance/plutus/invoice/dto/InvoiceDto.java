package com.finance.plutus.invoice.dto;

import com.finance.plutus.invoice.InvoiceStatus;
import com.finance.plutus.partner.dto.PartnerDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
}
