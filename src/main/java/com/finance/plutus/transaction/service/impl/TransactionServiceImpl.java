package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.transaction.model.CreateTransactionDto;
import com.finance.plutus.transaction.model.TransactionDto;
import com.finance.plutus.transaction.model.UpdateTransactionDto;
import com.finance.plutus.transaction.service.TransactionCleaner;
import com.finance.plutus.transaction.service.TransactionCreator;
import com.finance.plutus.transaction.service.TransactionFinder;
import com.finance.plutus.transaction.service.TransactionService;
import com.finance.plutus.transaction.service.TransactionUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionFinder transactionFinder;
  private final TransactionCleaner transactionCleaner;
  private final TransactionUpdater transactionUpdater;
  private final TransactionCreator transactionCreator;

  @Override
  public UUID create(CreateTransactionDto transaction) {
    return transactionCreator.create(transaction);
  }

  @Override
  public List<TransactionDto> findAll(int page, int size) {
    return transactionFinder.findAll(PageRequest.of(page, size)).stream()
        .map(TransactionDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void update(UUID id, UpdateTransactionDto transaction) {
    transactionUpdater.update(id, transaction);
  }

  @Override
  public void markAsDone(UUID id) {
    transactionUpdater.markAsDone(id);
  }

  @Override
  public void delete(UUID id) {
    transactionCleaner.delete(id);
  }

  @Override
  public long count() {
    return transactionFinder.count();
  }
}
