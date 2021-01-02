package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.service.PartnerFinder;
import com.finance.plutus.transaction.exception.WrongTransactionStatusException;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionStatus;
import com.finance.plutus.transaction.model.UpdateTransactionDto;
import com.finance.plutus.transaction.repository.TransactionRepository;
import com.finance.plutus.transaction.service.TransactionFinder;
import com.finance.plutus.transaction.service.TransactionUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionUpdaterImpl implements TransactionUpdater {

  private final PartnerFinder partnerFinder;
  private final TransactionFinder transactionFinder;
  private final TransactionRepository transactionRepository;

  @Override
  @Transactional
  public void update(UUID id, UpdateTransactionDto updateTransactionDto) {
    Transaction transaction = transactionFinder.findById(id);
    if (transaction.getStatus() == TransactionStatus.DONE) {
      throw new WrongTransactionStatusException();
    }
    Partner partner = partnerFinder.findById(updateTransactionDto.getPartnerId());
    transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    transaction.setMethod(updateTransactionDto.getMethod());
    transaction.setType(updateTransactionDto.getType());
    transaction.setDate(updateTransactionDto.getDate());
    transaction.setDocument(updateTransactionDto.getDocument());
    transaction.setDetails(updateTransactionDto.getDetails());
    transaction.setValue(updateTransactionDto.getValue());
    transaction.setPartner(partner);
    transactionRepository.save(transaction);
  }

  @Override
  @Transactional
  public void collect(UUID id) {
    Transaction transaction = transactionFinder.findById(id);
    collect(transaction);
  }

  @Override
  @Transactional
  public void collect(Iterable<UUID> ids) {
    List<Transaction> transactions = transactionFinder.findAllById(ids);
    transactions.forEach(this::collect);
  }

  private void collect(Transaction transaction) {
    if (transaction.getStatus() != TransactionStatus.DONE) {
      transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setStatus(TransactionStatus.DONE);
      transactionRepository.save(transaction);
    }
  }
}
