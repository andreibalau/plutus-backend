package com.finance.plutus.controller.payload;

import java.util.List;

import com.finance.plutus.model.dto.PreviewInvoiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindInvoicesResponse {
  private List<PreviewInvoiceDto> invoices;
  private int page;
  private int size;
}
