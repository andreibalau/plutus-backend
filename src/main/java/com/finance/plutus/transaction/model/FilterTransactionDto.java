package com.finance.plutus.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/** Plutus Created by Catalin on 12/22/2020 */
@Getter
@Setter
public class FilterTransactionDto {
  private UUID partnerId;
  private TransactionType type;
  private LocalDate startDate;
  private LocalDate endDate;
}
