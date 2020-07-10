package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.InvoiceStatus;
import com.finance.plutus.model.entity.InvoiceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class InvoiceDto {
  private Long id;
  private String name;
  private PreviewPartnerDto partner;
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
}
