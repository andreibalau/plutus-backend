package com.finance.plutus.transaction;

import com.finance.plutus.transaction.dto.CreateTransactionDto;
import com.finance.plutus.transaction.dto.UpdateTransactionDto;
import com.finance.plutus.transaction.dto.UploadFileDto;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface TransactionService {
  void delete(UUID id);

  byte[] downloadAll();

  UUID create(CreateTransactionDto createTransactionDto);

  void create(UploadFileDto uploadFileDto);

  List<Transaction> findAll(
      int page,
      int size,
      UUID partnerId,
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate);

  List<Transaction> findAll(
      UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate);

  List<Transaction> findAllById(Iterable<UUID> ids);

  List<Transaction> findAll();

  Transaction findById(UUID id);

  void update(UUID id, UpdateTransactionDto updateTransactionDto);

  void collect(UUID id);

  void collect(Iterable<UUID> ids);
}
