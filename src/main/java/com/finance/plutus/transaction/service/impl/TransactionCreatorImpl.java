package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.service.PartnerFinder;
import com.finance.plutus.transaction.model.CreateTransactionDto;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionStatus;
import com.finance.plutus.transaction.repository.TransactionRepository;
import com.finance.plutus.transaction.service.TransactionCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionCreatorImpl implements TransactionCreator {

  private final PartnerFinder partnerFinder;
  private final TransactionRepository transactionRepository;

  @Override
  @Transactional
  public UUID create(CreateTransactionDto createTransactionDto) {
    Transaction transaction = createTransaction(createTransactionDto);
    transactionRepository.save(transaction);
    return transaction.getId();
  }

  private Transaction createTransaction(CreateTransactionDto createTransactionDto) {
    Partner partner = partnerFinder.findById(createTransactionDto.getPartnerId());
    Transaction transaction = new Transaction();
    transaction.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    transaction.setMethod(createTransactionDto.getMethod());
    transaction.setType(createTransactionDto.getType());
    transaction.setStatus(TransactionStatus.DRAFT);
    transaction.setDate(createTransactionDto.getDate());
    transaction.setDocument(createTransactionDto.getDocument());
    transaction.setDetails(createTransactionDto.getDetails());
    transaction.setValue(createTransactionDto.getValue());
    transaction.setPartner(partner);
    return transaction;
  }
}
