package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.FilterTransactionDto;
import com.finance.plutus.transaction.model.Transaction;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionFinder {
  List<Transaction> findAll(PageRequest page);

  List<Transaction> findAllById(Iterable<UUID> ids);

  List<Transaction> findAllFiltered(PageRequest page, FilterTransactionDto filter);

  Transaction findById(UUID id);

  long count();

  long countWithFilter(FilterTransactionDto filter);
}
