package com.finance.plutus.transaction.repository;

import com.finance.plutus.transaction.model.Transaction;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/** Plutus Created by Catalin on 12/22/2020 */
public interface TransactionRepositoryFilter {
  List<Transaction> findAllFiltered(Map<String, Object> params, PageRequest page);

  List<Transaction> findAllFiltered(Map<String, Object> params);
}
