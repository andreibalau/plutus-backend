package com.finance.plutus.bank.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.bank.Bank;
import com.finance.plutus.bank.BankRepository;
import com.finance.plutus.bank.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

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
