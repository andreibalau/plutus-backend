package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.UpdateTransactionDto;

import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionUpdater {
  void update(UUID id, UpdateTransactionDto transaction);
}
