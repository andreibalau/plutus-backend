package com.finance.plutus.old.service.transaction;

import com.finance.plutus.old.model.dto.CreateTransactionDto;

import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionCreator {
  UUID create(CreateTransactionDto transaction);
}
