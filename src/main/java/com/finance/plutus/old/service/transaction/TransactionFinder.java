package com.finance.plutus.old.service.transaction;

import com.finance.plutus.old.model.entity.Transaction;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionFinder {
  List<Transaction> findAll();
}
