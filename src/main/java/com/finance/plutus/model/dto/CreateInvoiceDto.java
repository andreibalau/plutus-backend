package com.finance.plutus.model.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.InvoiceType;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceDto {
  @NotNull private Long partner;
  @NotNull private LocalDate date;
  @NotNull private LocalDate dueDate;
  @NotNull private InvoiceType type;
  @NotNull private Currency currency;
  @NotNull private Long serial;
  private List<CreateInvoiceLineDto> lines;
}
