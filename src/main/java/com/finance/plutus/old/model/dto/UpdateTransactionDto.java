package com.finance.plutus.old.model.dto;

import com.finance.plutus.old.model.entity.TransactionMethod;
import com.finance.plutus.old.model.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
@Getter
@Setter
public class UpdateTransactionDto {
  @NotNull private LocalDate date;
  @NotBlank private String document;
  @NotBlank private String details;
  @NotNull private UUID partnerId;
  @NotNull private TransactionType type;
  @NotNull private TransactionMethod method;
}
