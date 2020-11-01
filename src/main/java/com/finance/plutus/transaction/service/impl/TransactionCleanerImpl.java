package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.transaction.exception.WrongTransactionStatusException;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionStatus;
import com.finance.plutus.transaction.repository.TransactionRepository;
import com.finance.plutus.transaction.service.TransactionCleaner;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionCleanerImpl implements TransactionCleaner {

  private final TransactionFinder transactionFinder;
  private final TransactionRepository transactionRepository;

  @Override
  @Transactional
  public void delete(UUID id) {
    Transaction transaction = transactionFinder.findById(id);
    if (transaction.getStatus() == TransactionStatus.DONE) {
      throw new WrongTransactionStatusException();
    }
    transactionRepository.delete(transaction);
  }
}
