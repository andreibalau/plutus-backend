package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.PreviewInvoiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 7/3/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindInvoicesResponse {
  private List<PreviewInvoiceDto> invoices;
  private int page;
  private int size;
}