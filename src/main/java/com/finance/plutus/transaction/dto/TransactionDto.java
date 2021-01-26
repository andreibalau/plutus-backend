package com.finance.plutus.transaction.dto;

import com.finance.plutus.partner.dto.PartnerDto;
import com.finance.plutus.transaction.model.TransactionMethod;
import com.finance.plutus.transaction.model.TransactionStatus;
import com.finance.plutus.transaction.model.TransactionType;
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
  private Boolean deductible;
  private TransactionCurrencyDto currency;
}
