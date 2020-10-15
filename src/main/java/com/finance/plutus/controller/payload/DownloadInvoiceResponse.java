package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.InvoiceHtmlDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 10/15/2020 */
@Getter
@Setter
@AllArgsConstructor
public class DownloadInvoiceResponse {
  private InvoiceHtmlDto invoice;
}
