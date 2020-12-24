package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.transaction.model.FilterTransactionDto;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.repository.TransactionRepository;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public List<Transaction> findAllById(Iterable<UUID> ids) {
    return transactionRepository.findAllById(ids);
  }

  @Override
  public List<Transaction> findAllFiltered(PageRequest page, FilterTransactionDto filter) {
    Map<String, Object> params = createFilterParams(filter);
    return transactionRepository.findAllFiltered(params, page);
  }

  @Override
  public List<Transaction> findAllFiltered(FilterTransactionDto filter) {
    Map<String, Object> params = createFilterParams(filter);
    return transactionRepository.findAllFiltered(params);
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

  @Override
  public long countWithFilter(FilterTransactionDto filter) {
    Map<String, Object> params = createFilterParams(filter);
    return transactionRepository.countWithFilter(params);
  }

  private Map<String, Object> createFilterParams(FilterTransactionDto filter) {
    Map<String, Object> params = new HashMap<>();
    if (filter.getPartnerId() != null) {
      params.put("partnerId", filter.getPartnerId());
    }
    if (filter.getType() != null) {
      params.put("type", filter.getType());
    }
    if (filter.getStartDate() != null) {
      params.put("startDate", filter.getStartDate());
    }
    if (filter.getEndDate() != null) {
      params.put("endDate", filter.getEndDate());
    }
    return params;
  }
}
