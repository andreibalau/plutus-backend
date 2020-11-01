package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.CreateTransactionDto;
import com.finance.plutus.transaction.model.TransactionDto;
import com.finance.plutus.transaction.model.UpdateTransactionDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface TransactionService {
  UUID create(CreateTransactionDto transaction);

  List<TransactionDto> findAll(int page, int size);

  void update(UUID id, UpdateTransactionDto transaction);

  void markAsDone(UUID id);

  void markAsDone(Iterable<UUID> ids);

  void importFile(String transactionsFile);

  void delete(UUID id);

  long count();
}
