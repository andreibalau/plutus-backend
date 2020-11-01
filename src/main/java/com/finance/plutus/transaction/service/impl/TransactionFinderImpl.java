package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.repository.TransactionRepository;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionFinderImpl implements TransactionFinder {

  private final TransactionRepository transactionRepository;

  @Override
  public List<Transaction> findAll(PageRequest page) {
    return transactionRepository.findAll(page).stream().collect(Collectors.toList());
  }

  @Override
  public Transaction findById(UUID id) {
    return transactionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("transaction"));
  }

  @Override
  public long count() {
    return transactionRepository.count();
  }
}
