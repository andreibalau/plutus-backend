package com.finance.plutus.bank.service;

import com.finance.plutus.bank.model.Bank;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface BankFinder {
  Bank findById(UUID id);

  List<Bank> findAll();
}
