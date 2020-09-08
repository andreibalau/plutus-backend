package com.finance.plutus.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 9/8/2020 */
@Getter
@Setter
@AllArgsConstructor
public class DownloadInvoiceResponse {
  private byte[] content;
}
