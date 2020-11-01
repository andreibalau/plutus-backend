package com.finance.plutus.invoice.controller.payload;

import com.finance.plutus.invoice.model.CreateInvoiceDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceRequest {
  @Valid @NotNull private CreateInvoiceDto invoice;
}
