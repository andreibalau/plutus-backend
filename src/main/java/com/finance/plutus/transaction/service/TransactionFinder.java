package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionFinder {
  List<Transaction> findAll(
      PageRequest page,
      UUID partnerId,
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate);

  List<Transaction> findAll(
      UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate);

  List<Transaction> findAllById(Iterable<UUID> ids);

  Transaction findById(UUID id);

  long count(UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate);
}
