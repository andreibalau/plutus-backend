package com.finance.plutus.invoice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.finance.plutus.currency.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/25/2021 */
@Getter
@Setter
public class UpdateInvoiceDto {
  @NotNull private UUID partnerId;
  @NotNull private Currency currency;

  @NotNull
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dueDate;

  private List<UpdateInvoiceLineDto> lines;
}
