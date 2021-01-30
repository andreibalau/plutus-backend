package com.finance.plutus.transaction;

import com.finance.plutus.transaction.dto.*;
import com.finance.plutus.transaction.model.Transaction;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface TransactionService {
  UUID create(CreateTransactionDto createTransactionDto);

  void create(UploadFileDto uploadFileDto);

  void update(UUID id, UpdateTransactionDto updateTransactionDto);

  List<Transaction> findAll(FilterParams filterParams, int page, int size);

  List<Transaction> findAll(FilterParams filterParams);

  List<Transaction> findAllById(Iterable<UUID> ids);

  List<Transaction> findAll();

  Transaction findById(UUID id);

  TransactionStat calculateStat(FilterParams filterParams);

  void delete(UUID id);

  byte[] downloadDocument(String year);

  void collect(UUID id);

  void collect(Iterable<UUID> ids);
}
