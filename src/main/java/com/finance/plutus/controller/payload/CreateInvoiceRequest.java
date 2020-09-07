package com.finance.plutus.controller.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.dto.CreateInvoiceDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceRequest {
  @Valid @NotNull private CreateInvoiceDto invoice;
}
