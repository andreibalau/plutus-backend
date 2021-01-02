package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;
import com.finance.plutus.transaction.repository.TransactionRepository;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionFinderImpl implements TransactionFinder {

  private final TransactionRepository transactionRepository;

  @Override
  public List<Transaction> findAll(
      PageRequest page,
      UUID partnerId,
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate) {
    Map<String, Object> params = createFilterParams(partnerId, type, startDate, endDate);
    return transactionRepository.findAllFiltered(params, page);
  }

  @Override
  public List<Transaction> findAll(
      UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate) {
    Map<String, Object> params = createFilterParams(partnerId, type, startDate, endDate);
    return transactionRepository.findAllFiltered(params);
  }

  @Override
  public List<Transaction> findAllById(Iterable<UUID> ids) {
    return transactionRepository.findAllById(ids);
  }

  @Override
  public Transaction findById(UUID id) {
    return transactionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("transaction"));
  }

  @Override
  public long count(UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate) {
    Map<String, Object> params = createFilterParams(partnerId, type, startDate, endDate);
    return transactionRepository.countWithFilter(params);
  }

  private Map<String, Object> createFilterParams(
      UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate) {
    Map<String, Object> params = new HashMap<>();
    if (partnerId != null) {
      params.put("partnerId", partnerId);
    }
    if (type != null) {
      params.put("type", type);
    }
    if (startDate != null) {
      params.put("startDate", startDate);
    }
    if (endDate != null) {
      params.put("endDate", endDate);
    }
    return params;
  }
}
