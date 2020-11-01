package com.finance.plutus.transaction.service;

import com.finance.plutus.transaction.model.Transaction;

import java.util.List;

/** Plutus Created by Catalin on 10/31/2020 */
public interface TransactionFinder {
  List<Transaction> findAll();
}
