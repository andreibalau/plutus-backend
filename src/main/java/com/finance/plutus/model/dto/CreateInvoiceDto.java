package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.InvoiceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceDto {
  private Long partner;
  private LocalDate date;
  private LocalDate dueDate;
  private InvoiceType type;
  private Currency currency;
  private List<CreateInvoiceLineDto> lines;
}
