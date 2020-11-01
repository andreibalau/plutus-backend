package com.finance.plutus.transaction.service;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface TransactionCleaner {
  void delete(UUID id);
}
