package com.finance.plutus.invoice.controller.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/** Plutus Created by Catalin on 11/1/2020 */
@Getter
@Setter
public class ImportFileRequest {
  @NotBlank private String invoicesFile;
}
