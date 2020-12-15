package com.finance.plutus.transaction.model;

import com.finance.plutus.partner.model.PartnerDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Getter
@Setter
public class TransactionDto {
  private UUID id;
  private LocalDate date;
  private String document;
  private String details;
  private PartnerDto partner;
  private TransactionType type;
  private TransactionMethod method;
  private Double value;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
  private TransactionStatus status;

  public static TransactionDto mapFromEntity(Transaction transaction) {
    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setId(transaction.getId());
    transactionDto.setDate(transaction.getDate());
    transactionDto.setDocument(transaction.getDocument());
    transactionDto.setDetails(transaction.getDetails());
    transactionDto.setPartner(PartnerDto.mapFromEntity(transaction.getPartner()));
    transactionDto.setType(transaction.getType());
    transactionDto.setMethod(transaction.getMethod());
    transactionDto.setCreatedOn(transaction.getCreatedOn());
    transactionDto.setUpdatedOn(transaction.getUpdatedOn());
    transactionDto.setValue(transaction.getValue());
    transactionDto.setStatus(transaction.getStatus());
    return transactionDto;
  }
}
