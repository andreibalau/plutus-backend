package com.finance.plutus.transaction;

import com.finance.plutus.partner.PartnerMapper;
import com.finance.plutus.transaction.dto.CreateTransactionDto;
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
    if (createTransactionDto.getCurrency() != null
        && createTransactionDto.getCurrencyValue() != null) {
      TransactionCurrency transactionCurrency = new TransactionCurrency();
      transactionCurrency.setCurrency(createTransactionDto.getCurrency());
      transactionCurrency.setValue(createTransactionDto.getValue());
      transactionCurrency.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transactionCurrency.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setTransactionCurrency(transactionCurrency);
    }
    return transaction;
  }
}
