package com.finance.plutus.controller.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 9/8/2020 */
@Getter
@Setter
public class DownloadInvoicesRequest {
  private List<String> invoicesIds;
}
