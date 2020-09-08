package com.finance.plutus.controller.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 9/8/2020 */
@Getter
@Setter
public class DownloadInvoicesRequest {
  private List<Long> invoicesIds;
}
