package com.finance.plutus.transaction.controller.payload;

import com.finance.plutus.transaction.model.UpdateTransactionDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 11/1/2020 */
@Getter
@Setter
public class UpdateTransactionRequest {
  @Valid @NotNull private UpdateTransactionDto transaction;
}
