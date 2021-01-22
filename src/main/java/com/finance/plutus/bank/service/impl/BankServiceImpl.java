package com.finance.plutus.bank.service.impl;

import com.finance.plutus.bank.infrastructure.adapters.secondary.Bank;
import com.finance.plutus.bank.infrastructure.adapters.primary.BankDto;
import com.finance.plutus.bank.core.application.BankFinder;
import com.finance.plutus.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 10/31/2020 */
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

  private final BankFinder bankFinder;

  @Override
  public BankDto findById(UUID id) {
    Bank bank = bankFinder.findById(id);
    return BankDto.mapFromEntity(bank);
  }

  @Override
  public List<BankDto> findAll() {
    return bankFinder.findAll().stream().map(BankDto::mapFromEntity).collect(Collectors.toList());
  }
}
