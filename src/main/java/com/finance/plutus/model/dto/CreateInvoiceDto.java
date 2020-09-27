package com.finance.plutus.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.InvoiceType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceDto {
  @NotNull private Long partner;

  @NotNull
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;

  @NotNull
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dueDate;

  @NotNull private InvoiceType type;
  @NotNull private Currency currency;
  @NotNull private Long serial;
  private List<CreateInvoiceLineDto> lines;
}
