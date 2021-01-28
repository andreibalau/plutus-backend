package com.finance.plutus.transaction.dto;

import com.finance.plutus.transaction.model.TransactionType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/** Plutus Created by Catalin on 1/25/2021 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterParams {
  private UUID partnerId;
  private TransactionType type;
  private Boolean deductible;
  private String startDate;
  private String endDate;
}
