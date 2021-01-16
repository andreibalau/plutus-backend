package com.finance.plutus.transaction.model.html;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Plutus Created by Catalin on 1/16/2021 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLine {
  private String number;
  private String date;
  private String document;
  private String partner;
  private String details;
  private String income;
  private String expense;
}
