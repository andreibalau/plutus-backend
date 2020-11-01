package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.CreateTransactionDto;

import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionCreator {
  UUID create(CreateTransactionDto transaction);
}
