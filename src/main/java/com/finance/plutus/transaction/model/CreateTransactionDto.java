package com.finance.plutus.transaction.model;

import com.finance.plutus.transaction.model.TransactionMethod;
import com.finance.plutus.transaction.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
@Getter
@Setter
public class CreateTransactionDto {
  @NotNull private LocalDate date;
  @NotBlank private String document;
  @NotBlank private String details;
  @NotNull private UUID partnerId;
  @NotNull private TransactionType type;
  @NotNull private TransactionMethod method;
}
