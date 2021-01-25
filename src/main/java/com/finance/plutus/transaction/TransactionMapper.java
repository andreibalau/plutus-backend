package com.finance.plutus.transaction;

import com.finance.plutus.currency.Currency;
import com.finance.plutus.partner.PartnerMapper;
import com.finance.plutus.transaction.dto.CreateTransactionDto;
import com.finance.plutus.transaction.dto.CreateTransactionFileDto;
import com.finance.plutus.transaction.dto.TransactionDto;
import com.finance.plutus.transaction.dto.UpdateTransactionDto;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionCurrency;
import com.finance.plutus.transaction.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
@RequiredArgsConstructor
public class TransactionMapper {

  private final PartnerMapper partnerMapper;

  public TransactionDto mapToDto(Transaction transaction) {
    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setId(transaction.getId());
    transactionDto.setDate(transaction.getDate());
    transactionDto.setDocument(transaction.getDocument());
    transactionDto.setDetails(transaction.getDetails());
    transactionDto.setPartner(partnerMapper.mapToDto(transaction.getPartner()));
    transactionDto.setType(transaction.getType());
    transactionDto.setMethod(transaction.getMethod());
    transactionDto.setValue(transaction.getValue());
    transactionDto.setDeductible(transaction.getDeductible());
    transactionDto.setStatus(transaction.getStatus());
    return transactionDto;
  }

  public Transaction mapToEntity(
      Transaction transaction, UpdateTransactionDto updateTransactionDto) {
    transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    transaction.setMethod(updateTransactionDto.getMethod());
    transaction.setType(updateTransactionDto.getType());
    transaction.setDate(updateTransactionDto.getDate());
    transaction.setDocument(updateTransactionDto.getDocument());
    transaction.setDetails(updateTransactionDto.getDetails());
    transaction.setValue(updateTransactionDto.getValue());
    transaction.setDeductible(updateTransactionDto.getDeductible());
    if (updateTransactionDto.getCurrency() != Currency.RON) {
      TransactionCurrency transactionCurrency = transaction.getTransactionCurrency();
      if (transactionCurrency == null) {
        transactionCurrency = new TransactionCurrency();
        transactionCurrency.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
      }
      transactionCurrency.setCurrency(updateTransactionDto.getCurrency());
      transactionCurrency.setValue(updateTransactionDto.getValue());
      transactionCurrency.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setTransactionCurrency(transactionCurrency);
    }
    return transaction;
  }

  public Transaction mapToEntity(CreateTransactionDto createTransactionDto) {
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
    transaction.setDeductible(createTransactionDto.getDeductible());
    if (createTransactionDto.getCurrency() != Currency.RON) {
      TransactionCurrency transactionCurrency = new TransactionCurrency();
      transactionCurrency.setCurrency(createTransactionDto.getCurrency());
      transactionCurrency.setValue(createTransactionDto.getValue());
      transactionCurrency.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transactionCurrency.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setTransactionCurrency(transactionCurrency);
    }
    return transaction;
  }

  public Transaction mapToEntity(CreateTransactionFileDto createTransactionFileDto) {
    Transaction transaction = new Transaction();
    transaction.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    transaction.setMethod(createTransactionFileDto.getMethod());
    transaction.setType(createTransactionFileDto.getType());
    transaction.setStatus(TransactionStatus.DRAFT);
    transaction.setDate(createTransactionFileDto.getDate());
    transaction.setDocument(createTransactionFileDto.getDocument());
    transaction.setDetails(createTransactionFileDto.getDetails());
    transaction.setValue(createTransactionFileDto.getValue());
    transaction.setDeductible(createTransactionFileDto.getDeductible());
    if (createTransactionFileDto.getCurrency() != null
        && createTransactionFileDto.getCurrencyValue() != null
        && createTransactionFileDto.getCurrency() != Currency.RON) {
      TransactionCurrency transactionCurrency = new TransactionCurrency();
      transactionCurrency.setCurrency(createTransactionFileDto.getCurrency());
      transactionCurrency.setValue(createTransactionFileDto.getValue());
      transactionCurrency.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transactionCurrency.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setTransactionCurrency(transactionCurrency);
    }
    return transaction;
  }
}
