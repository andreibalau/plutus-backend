package com.finance.plutus.transaction.model.html;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/** Plutus Created by Catalin on 1/16/2021 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
  private String year;
  private List<TransactionLine> transactions;
}
