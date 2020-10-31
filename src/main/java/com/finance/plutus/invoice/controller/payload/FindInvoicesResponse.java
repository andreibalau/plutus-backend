package com.finance.plutus.invoice.controller.payload;

import com.finance.plutus.old.model.dto.InvoiceDto;
import lombok.*;

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
