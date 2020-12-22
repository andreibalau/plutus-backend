package com.finance.plutus.bank.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.bank.model.Bank;
import com.finance.plutus.bank.repository.BankRepository;
import com.finance.plutus.bank.service.BankFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class BankFinderImpl implements BankFinder {

  private final BankRepository bankRepository;

  @Override
  public Bank findById(UUID id) {
    return bankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("bank"));
  }

  @Override
  public List<Bank> findAll() {
    return bankRepository.findAll();
  }
}
