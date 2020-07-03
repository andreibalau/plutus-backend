package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.CreateInvoiceDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
public class CreateInvoiceRequest {
  private CreateInvoiceDto invoice;
}
