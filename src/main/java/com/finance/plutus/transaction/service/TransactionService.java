package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.CreateTransactionDto;
import com.finance.plutus.transaction.model.TransactionDto;
import com.finance.plutus.transaction.model.TransactionType;
import com.finance.plutus.transaction.model.UpdateTransactionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface TransactionService {
  UUID create(CreateTransactionDto transaction);

  List<TransactionDto> findAll(
      int page,
      int size,
      UUID partnerId,
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate);

  void update(UUID id, UpdateTransactionDto transaction);

  void markAsDone(UUID id);

  void markAsDone(Iterable<UUID> ids);

  void importFile(String transactionsFile);

  byte[] exportFile();

  void delete(UUID id);

  long count(UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate);
}
