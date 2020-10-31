package com.finance.plutus.bank.service;

import com.finance.plutus.bank.model.BankDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
public interface BankService {
  BankDto findById(UUID id);

  List<BankDto> findAll();
}
