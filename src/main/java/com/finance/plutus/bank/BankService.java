package com.finance.plutus.bank;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface BankService {
  Bank findById(UUID id);

  List<Bank> findAll();
}
