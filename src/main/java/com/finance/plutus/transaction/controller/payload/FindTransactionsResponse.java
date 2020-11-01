package com.finance.plutus.transaction.controller.payload;

import com.finance.plutus.transaction.model.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/** Plutus Created by Catalin on 11/1/2020 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindTransactionsResponse {
  private int page;
  private int size;
  private long total;
  private List<TransactionDto> transactions;
}
