package com.finance.plutus.invoice.controller.payload;

import com.finance.plutus.invoice.model.InvoiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindInvoicesResponse {
  private int page;
  private int size;
  private long total;
  private List<InvoiceDto> invoices;
}
