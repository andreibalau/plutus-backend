package com.finance.plutus.old.controller.payload;

import com.finance.plutus.old.model.dto.InvoiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindInvoicesResponse {
  private List<InvoiceDto> invoices;
  private int page;
  private int size;
  private long total;
}
