package com.finance.plutus.transaction.controller.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/** Plutus Created by Catalin on 11/1/2020 */
@Getter
@Setter
public class ImportFileRequest {
  @NotBlank private String transactionsFile;
}
